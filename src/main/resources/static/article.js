const deleteBtn = document.getElementById('delete-btn');

if(deleteBtn){
    deleteBtn.addEventListener('click', function (e){
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            })
    })
}