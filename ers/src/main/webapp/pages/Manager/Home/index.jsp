<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ERS - Home</title>
    <link rel="stylesheet" type="text/css" href="/pages/nav.css">
    <link rel="stylesheet" type="text/css" href="/pages/Manager/Home/index.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <script defer src="/pages/Manager/Home/index.js"></script>
</head>
<body>
    <nav class='navbar'>
        <div class="navDiv">
            <a class="navA" href="">
                <img class="navImg" src="https://img.icons8.com/small/32/ffffff/home.png"/>
            </a>
        </div>
        <div class="navDiv">
            <a class="navA" href=""></a>
        </div>
        <div class="navDiv">
            <a class="navA" href="/manager/logout">
                <img class="logout-icon" src="/pages/logout.png"/>
            </a>
        </div>
    </nav>
    <div class="heading-container">
        <h1 id="pending-title" class="title selected">Pending</h1>
        <h1 class="divider">/</h1>
        <h1 id="past-title" class="title">Past</h1>
    </div>
    <div class="tickets-container">
    </div>
</body>
</html>