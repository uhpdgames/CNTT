$(document).on("pagecreate", function() {
    $("body > [data-role='panel']").panel();
    $("body > [data-role='panel'] [data-role='listview']").listview();
});

var lay_du_lieu = function(key, file) {
    var url = "file:///android_asset/www/" + "data/" + key + "/" + file + ".json";
    $.getJSON(url, function(d) {
        //console.log(d);
        for (var b = 0; b < d.length; b++) {
            var val = d[b];
            var row = $("<tr />");
            if (key == 'mh_tq') {
                $("#bang-mon-tq").append(row);
                col_custom(row, val);
                row.append($("<td>" + val.mh_tq + "</td>"));
            } else if (key == 'mh_truoc') {
                $("#bang-mon-hoc-truoc").append(row);
                col_custom(row, val);
                row.append($("<td>" + val.mh_truoc + "</td>"));
            }
        }
    });
}
var col_custom = function(row, d) {
    row.append($("<td>" + d.mamh + "</td>"));
    row.append($("<td>" + d.tenmh + "</td>"));
    row.append($("<td>" + d.sotc_lt + "</td>"));
    row.append($("<td>" + d.sotc_th + "</td>"));
}