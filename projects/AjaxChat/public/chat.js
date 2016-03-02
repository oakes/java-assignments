function addMessage() {
    $.post(
        "/add-message",
        {
            author: $("#author").val(),
            text: $("#text").val()
        },
        function(data) {
            $("#author").val("");
            $("#text").val("");
            getMessages();
        }
    );
}

function getMessages() {
    $.get(
        "/get-messages",
        function(data) {
            var messages = JSON.parse(data);
            $("#messages").empty();
            for (var i in messages) {
                var elem = $("<div></div>").text(messages[i].author + ": " + messages[i].text);
                $("#messages").append(elem);
            }
        }
    );
}

setInterval(getMessages, 1000);