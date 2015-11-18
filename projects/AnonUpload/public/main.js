function getFiles(filesData) {
    for (var i in filesData) {
        var elem = $("<a>");
        elem.attr("href", filesData[i].name);
        elem.text(filesData[i].originalName);
        $("#fileList").append(elem);
        var elem2 = $("<br>");
        $("#fileList").append(elem2);
    }
}

$.get("/files", getFiles);