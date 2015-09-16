<html>
<head>
    <title>Photo loading</title>
</head>
<body>
<%--
<?php
    if($_FILES["filename"]["size"] > 1024*1*1024) {
        echo ("Размер файла превышает 1 мегабайт");
        exit;
    }
    if (is_uploaded_file ($_FILES["photo"]["tmp_name"])) {
        move_uploaded_file ($_FILES["photo"]["tmp_name"], "/path/to/file/".$_FILES["photo"]["name"]);
    } else {
        echo("Ошибка загрузки файла");
    }
?>
--%>

<?php
$uploaddir = './files/';
$uploadfile = $uploaddir.basename($_FILES['photo']['name']);
if (copy($_FILES['photo']['tmp_name'], $uploadfile))
{
echo "<h3>Файл успешно загружен на сервер</h3>";
}
else { echo "<h3>Ошибка! Не удалось загрузить файл на сервер!</h3>"; exit; }
echo "<h3>Информация о загруженном на сервер файле: </h3>";
echo "<p><b>Оригинальное имя загруженного файла: ".$_FILES['photo']['name']."</b></p>";
echo "<p><b>Mime-тип загруженного файла: ".$_FILES['photo']['type']."</b></p>";
echo "<p><b>Размер загруженного файла в байтах: ".$_FILES['photo']['size']."</b></p>";
echo "<p><b>Временное имя файла: ".$_FILES['photo']['tmp_name']."</b></p>";

?>
</body>
</html>