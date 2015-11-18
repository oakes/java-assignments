function getItems(itemData) {
    $("#items").empty();
    for (var i in itemData) {
        var elem = $("<div>").text(itemData[i].text);
        $("#items").append(elem);
    }
}

$.get("/items", getItems