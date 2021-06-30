function validate(check){
    if (check ==""){
        alert('공백금지!!!!!!')
        return false;
    }
}
// <---------------------보드페이지---------------------------->
//보드 리스트
function getBoardList(){
    $.ajax({
        type: 'GET',
        url: '/api/board',
        success: function (response) {
            if(response!=null){
                for (let i = 0; i < response.length; i++) {
                    let boardDto =response[i];
                    let tempHtml = addHTML(boardDto);
                    // $('#tbody').empty()
                    $('#tbody').append(tempHtml)
                }
                $('#datatable1').DataTable({
                    "language": {
                        "emptyTable": "게시글이 없어요.",
                        "lengthMenu": "_MENU_ 개씩 보기",
                        "info": "현재 _START_ - _END_ / _TOTAL_건",
                        "infoEmpty": "게시글이 없어요",
                        "infoFiltered": "( _MAX_건의 게시글에서 필터링됨 )",
                        "search": "에서 검색: ",
                        "zeroRecords": "일치하는 데이터가 없어요.",
                        "loadingRecords": "로딩중...",
                        "processing":     "잠시만 기다려 주세요...",
                        "paginate": {
                            "next": "다음",
                            "previous": "이전"
                        }
                    },
                    order: [ [3, "desc" ] ],
                    ordering : false
                })
             }else{
                $('#datatable1').DataTable({
                    "language": {
                        "emptyTable": "게시글이 없어요.",
                        "lengthMenu": "_MENU_ 개씩 보기",
                        "info": "현재 _START_ - _END_ / _TOTAL_건",
                        "infoEmpty": "게시글이 없어요",
                        "infoFiltered": "( _MAX_건의 게시글에서 필터링됨 )",
                        "search": "에서 검색: ",
                        "zeroRecords": "일치하는 데이터가 없어요.",
                        "loadingRecords": "로딩중...",
                        "processing":     "잠시만 기다려 주세요...",
                        "paginate": {
                        "next": "다음",
                        "previous": "이전"
                        }
                    },
                    order: [ [3, "desc" ] ]
                    , ordering : false
                })
             }
         }
    })
}
function addHTML(boardDto){
    let date_1 = boardDto.createdAt
    console.log(date_1)
    console.log(boardDto.id)

    let javaDate = new Date(boardDto.createdAt);
    let year = javaDate.getFullYear()
    let month = javaDate.getMonth()+1
    let day = javaDate.getDate()

    let createDate = year+"-"+month+"-"+day;
    return `
            <tr onClick="location.href='../detail.html?id=${boardDto.id}'" style="cursor:pointer;" class="active">
            <td>${boardDto.id}</td>
            <td>${boardDto.title}</td>
            <td>${boardDto.writer} </td>
            <td>${createDate}</td>
            </tr>
            `
}
// <=============================디테일페이지 ====================>
//디테일 불러오기
function getDetail(id) {
    let content = $('#contents')
    let title = $('#title')
    let writer = $('#writer')
    let createDate = $('#createDate')
    $.ajax({
        type: 'GET',
        url: `/api/board/detail/${id}`,
        success: function (response) {  let javaDate = new Date(response.createdAt);
            let year = javaDate.getFullYear()
            let month = javaDate.getMonth()+1
            let day = javaDate.getDate()
            let date = year + "-" + month + "-" + day;
            console.log(response.createdAt)
            console.log(date)
            title.html(response.title)
            $('#gotoTop').append('<input id="bId" type="hidden" value='+response.id+'>')

            content.text(response.content)
            writer.html(response.writer)
            createDate.text(date)
        }
    })
}
//삭제
function boardDelete(){
    let id = $('#bId').val();
    let content = $('#contents').text();
    let title = $('#title').text();
    let writer = $('#writer').text();
    let check_confirm = confirm('삭제하시겠습니까?!');
    if (check_confirm === true){
        let check_prompt = prompt("비밀번호 입력하세용","");
        if (check_prompt === "쿠쿠루삥뽕"){
            let data = {
                "content":content,
                "title":title,
                "writer":writer,
                "status": false
            };
            $.ajax({
                type: "PUT",
                url: '/api/board/delete/'+id,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    window.location.href="../board.html"
                }
            });
        }else if (check_prompt == null){
            return false;
        }else{
            alert("비밀번호도 모르는게 까불어!");
            window.location.href = "../index.html"
        }
    }else{
        return false
    }
}
//업데이트 뷰
function boardUpdateView(){
    let check = confirm('수정창으로 이동합니다?');
    if (check === true){
        $('#goEdit').empty();
        let temphtml =
            `<div class="table-responsive">
                <table class="table " style="text-align: center; border: 1px solid #dddddd">
                    <tbody>
                    <tr>
                        <td>
                            <input type="text" id="title" name="title" class="form-control" placeholder="글 제목"
                                   maxLength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="writer" name="writer" class="form-control" placeholder="작성자"
                                   maxLength="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <textarea id="contents" name="contents" class="form-control" placeholder="글 내용"
                                      maxLength="2048" style="height: 350px;"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="justify-content-between ">
                    <a href="../board.html"
                       class="button button-rounded button-reveal button-small button-white button-light text-right ">
                        <i class="icon-line-arrow-right"></i>
                        <span>목록으로</span>
                    </a>
                    <a href="javascript:void(0)" onClick="boardUpdate()"
                       class="button button-rounded button-reveal button-small button-white button-light text-right float-md-right ">
                        <i class="icon-line-arrow-right"></i>
                        <span>수정하기</span>
                    </a>
                </div>
            </div>`
        $('#goEdit').append(temphtml);
    }
}
//업데이트
function boardUpdate(){
    let check_confirm = confirm("수정하시겠습니까?")
    if(check_confirm === true){

        let check_prompt = prompt("비밀번호 입력하세용","");
        if(check_prompt === "쿠쿠루삥뽕"){
            let content = $('#contents').val()
            let title = $('#title').val()
            let writer = $('#writer').val()
            let id = $('#bId').val()
            if (validate(content) === false){
                return false;
            }else if(validate(title) === false){
                return false;
            }else if(validate(writer) === false){
                return false;
            }
            let data ={
                "content":content,
                "title":title,
                "writer":writer,
                "status":true
            };
            $.ajax({
                type: "PUT",
                url: "/api/board/update/"+id,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    alert('게시물이 성공적으로 수정되었습니다!');
                    location.reload();
                }
            });
        }else if(check_prompt === null){
            return false
        }else{
            alert('비밀번호도 모르는게 까불어!');
            window.location.href="../index.html"
        }
    }else{
        return false

    }
}
// <=============================글쓰기페이지========================>
function boardWrite() {
    let content = $('#contents').val();
    let title = $('#title').val();
    let writer = $('#writer').val();

    if (validate(content) === false){
        return false;
    }else if(validate(title) === false){
        return false;
    }else if(validate(writer) === false){
        return false;
    }
    let data = {
        'writer':writer,
        'title' :title,
        'content':content,
        'status':true
    };
    $.ajax({
        type:"POST",
        url:"/api/board",
        contentType:"application/json",
        data:JSON.stringify(data),
        success: function (response) {
            alert('게시글이 성공적으로 작성되었습니다!');
            window.history.go(-1)
        }
    });
}