let main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', () => { _this.save(); });
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
    }
}

main.init();