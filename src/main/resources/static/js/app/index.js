let main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', () => { _this.save(); });
        $('#btn-update').on('click', () => { _this.update(); });
        $('#btn-delete').on('click', () => { _this.delete(); });
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
        }).done(function() {
            alert('글이 등록됨');
            window.location.href = '/';
        }).fail(function(err) {
            alert(JSON.stringify(err))
        });
    },
    update: function() {
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        const id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다!');
            window.location.href = '/';
        }).fail(function(err) {
            alert(JSON.stringify(err))
        });
    },
    delete: function() {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8'
        }).done(function() {
            alert('삭제 완료!');
            window.location.href = '/';
        }).fail(function(err) {
            alert(JSON.stringify(err))
        });
    }
}

main.init();