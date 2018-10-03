<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>user_management</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.1.2/css/select.dataTables.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.0.2/css/responsive.dataTables.min.css"/>
    <script src="/static/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.1.2/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.0.2/js/dataTables.responsive.min.js"></script>
    <script src="/static/js/altEditor/dataTables.altEditor.free.js"></script>
    <script>
        var dataSet = [];
        var table;
        var columnDefs =
            [
                { title: "ID" },
                { title: "NAME" },
                { title: "FIRST NAME" },
                { title: "LAST NAME." },
                { title: "EMAIL" }
            ];
        $(document).ready( function () {
            $('#logout').on("click", function() {

                $.ajax({
                    type : 'post',
                    url : '../LoginControl',
                    data :JSON.stringify({
                        submit : "logout"
                    }),
                    contentType: "application/json; charset=utf-8",
                    dataType : 'json',

                    success: function(response) {
                        if (response.error == 0) {
                            location.href = "../homepage.jsp";
                        } else {
                            alert("modify error");
                        }
                    },
                    error: function() {
                    }
                });
            });

           table = $('#table_id').DataTable({
               dom: 'Bfrtip',        // element order: NEEDS BUTTON CONTAINER (B) ****
               select: 'single',     // enable single row selection
               responsive: true,     // enable responsiveness
               altEditor: true,      // Enable altEditor ****
               "columns" :columnDefs,
               buttons: [{
                   text: 'Add',
                   name: 'add'        // DO NOT change name
               },
                   {
                       extend: 'selected', // Bind to Selected row
                       text: 'Edit',
                       name: 'edit'        // DO NOT change name
                   },
                   {
                       extend: 'selected', // Bind to Selected row
                       text: 'Delete',
                       name: 'delete'      // DO NOT change name
                   }]
           });
        } );
        function upload() {
            var row_id = table.row().data()[0];
            var row_name = table.row().data()[1];
            var row_firstname = table.row().data()[2];
            var row_lastname = table.row().data()[3];
            var row_email = table.row().data()[4];

            $.ajax("/search_user", {
                type: "POST"
                , cache: false
                , dataType: "json"
                , async: true
                , data: {
                    type : "upload",
                    row_id : row_id,
                    row_name : row_name,
                    row_firstname : row_firstname,
                    row_lastname : row_lastname,
                    row_email : row_email
                }
                , success: function(ret) {
                   alert("success!");
                }
            });
            console.log(table.row().data());
        }


        function search_user() {
            var name = document.getElementById("user_input").value;

            $.ajax("/search_user", {
                type: "POST"
                , cache: false
                , dataType: "json"
                , async: true
                , data: {
                    type : "search",
                    input:name
                }
                , success: function(ret) {
                    var retData = new Array();
                    for(var i in ret) {
                        var temp = new Array();
                        var id = ret[i]["id"];temp.push(id);
                        var name = ret[i]["name"];temp.push(name);
                        var firstname = ret[i]["first name"];temp.push(firstname);
                        var lastname = ret[i]["last name"];temp.push(lastname);
                        var email = ret[i]["email"];temp.push(email);
                        retData.push(temp);
                    }
                    table.clear().rows.add(retData).draw();
                }
            });
        }
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Roommate Finder</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="dashboard"><a href="#">Dashboard</a></li>
                <li id="user_management"><a id="preli" href="user_management.html">User Management</a></li>
                <li id="houes_management"><a id="preli" href="#">House Management</a></li>
                <li id="crawler"><a id="crawler.jsp" href="#">House Data Management</a></li>
            </ul>
            <div id="known">
                <ul class="nav navbar-nav navbar-right">
                    <li id="user_name"><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
                    <li id="logout"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div style="padding: 50px 100px 10px;">
    <div class="col-lg-6 col-lg-offset-3">
         <h1>Search User</h1>
    </div>
    <br/>
    <form class="bs-example bs-example-form" role="form">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="input-group">
                    <input id="user_input" type="text" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="search_user()">Go!</button>
                    </span>
                </div>
            </div>
        </div>
    </form>
</div>
<hr />

<div class="col-lg-6 col-lg-offset-3">
    <table id="table_id" class="display" data-page-length='10'>
    </table>
</div>
<button class="btn btn-default" type="button" onclick="upload()">Upload</button>
</body>
</html>