<html>
<html xmlns:th="http://www.thymeleaf.org">
<head lang="en">
    <head>
        <title>Create</title>
    </head>
<body>
<label for = "code_snippet"><strong>Code input form</strong></label>
<textarea id="code_snippet" rows ="10" cols = "60" >// write your code snippet here</textarea>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
            alert("Success!");
        }
    }
</script>
<style>
    label {
        display:block;
        margin-bottom: 10px;
    }

    textarea {
        display:block;
        margin-bottom: 10px;
    }
    button {
        margin-top: 10px;
    }
</style>
</body>
</html>