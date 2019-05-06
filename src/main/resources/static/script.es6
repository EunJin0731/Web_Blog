let User = null;
let arrayId = null;
let now = null;

function openPostDialog() {
    $('#post_container').show(400);
}

function closePostDialog() {
    $('#post_container').hide(100);
}

function openEditDialog() {
    $('#edit_title').val(now.title);
    $('#edit_content').val(now.content);
    $('#posts_container').show(400);
}

function closePostDialog() {
    $('#posts_container').hide(100);
}


async function saveNewPost() {
    try {
        let requestData = {
            userId: User.name,
            title: $('#add_title').val().trim(),
            content: $('#add_content').val().trim()
        };

        let response = await $.ajax({
            url: 'http://localhost:8080/addPost',
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(requestData)
        });

        settingPost(response.data);
        recent_post(response.data);
        $('#post_container').hide(100);
    } catch (e) {
        console.log(JSON.stringify(e));
        $('#post_container').hide(100);
    }
}

async function fetchPostList() {
    try {
        let response = await $.get('http://localhost:8080/post');

        // $('#comments-list').html(JSON.stringify(response));
        for (let i = 0; i < response.data.length; i++) {
            recent_post(response.data[i]);
        }
    } catch (ex) {
        console.log(JSON.stringify(ex));
    }
}

async function post_move(button) {
    let response = await $.get('http://localhost:8080/post');
    let btn = $(button).text();
    if(btn === '이전'){
        if(arrayId === 0){
            alert("첫 페이지 입니다.");
            return;
        }
        arrayId = arrayId - 1;
        settingPost(response.data[arrayId]);
    }else if(btn === '다음'){
        if(arrayId === response.data.length - 1){
            alert("마지막 페이지 입니다.");
            return;
        }
        arrayId = arrayId + 1;
        settingPost(response.data[arrayId]);
    }
}

async function recent_post(response) {
        $('#recent_posts').prepend(
            `<span id="${response.id}" onclick="clickPost(${response.id})"><li>${response.title}</li></span>`
        );
}

async function clickPost(id) {
    let Post = await $.get('http://localhost:8080/view/'+id);
    settingPost(Post.data);
}

function settingPost(response) {
    $('#post_title').html(response.title);
    $('#post_content').html(response.content);
    $('#post_created').html(response.modified);
    $('#post_username').html(User.data.name);
    now = response;
}

async function settingInit() {
    let response = await $.get('http://localhost:8080/findUser/abc');
    let responses = await $.get('http://localhost:8080/PostCount/abc');
    User = response;
    let joindate = Math.floor((new Date() - new Date(response.data.created)) / (1000*60*60*24));
    let posts = 0;
    $('#user').append(
        `<img src="http://img.etoday.co.kr/pto_db/2019/01/600/20190110120117_1290112_650_975.jpg" width="150px" height="200px">
                <div>${response.data.account}</div>
                            <div>${response.data.name}</div>
                            <div>${response.data.created}</div>
                            <div>게시물 ${responses.data} 개</div>
                            <br/>
                            </div>`
    );

    TopPost();
}

async function TopPost(){
    let get = await $.get('/get/' + User.data.name);
    settingPost(get.data);
    TopArrayId();
}

async function TopArrayId(){
    let response = await $.get('http://localhost:8080/post');
    arrayId = response.data.length - 1;

}

async function editpost(button, id) {
    try {
        let requestData = {
            userId: User.name,
            title: $('#edit_title').val().trim(),
            content: $('#edit_content').val().trim()
        };

        let response = await $.ajax({
            url: 'http://localhost:8080/updatePost/'+now.id,
            type: 'put',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(requestData)
        });

        settingPost(response.data);
        $('#posts_container').hide(100);
    } catch (e) {
        console.log(JSON.stringify(e));
        $('#posts_container').hide(100);
    }
}

async function deletepost(button, id) {
        try {
            if (confirm('삭제하시겠습니까?') === true) {
                let response = await $.ajax({
                    type: 'delete',
                    url: `http://localhost:8080/deletePost/`+now.id
                });
                if (response.code === 202) $(`#${now.id}`).remove();
                else alert('삭제하지 못했습니다.');
            }
        } catch (err) {
            console.log(JSON.stringify(err));
        }
}

settingInit();
fetchPostList();