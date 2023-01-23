<!DOCTYPE html>
<html>

<head>
  <title>Admin Jeremy Deblaecker</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/png" href="../../Images/favicon1.png" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" media="screen" href="style.css" />
  <link rel="icon" type="image/png" href="../../Images/favicon1.png" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
  <meta charset="utf-8">
  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-143006450-1"></script>
  <script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
      dataLayer.push(arguments);
    }
    gtag('js', new Date());

    gtag('config', 'UA-143006450-1');
  </script>
</head>


<body>
  <header>
    <div class="topnav" id="myTopnav">
      <a href="../../index.html" class="active">Accueil</a>
      <a href="../cv/cv.html" class="#cv">CV</a>
      <a href="../projets/projets.html" class="#projets">Projets</a>
      <a href="../competence/competence.php" class="#competences">Compétences</a>
      <a href="../contact/contact.php" class="#contact">Contact</a>
      <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
    </div>
  </header>

  <div class="container">
    <br>
    <div class="form-group">
      <div class="input-group">
        <span class="input-group-addon">Rechercher</span>
        <input type="text" name="search_text" id="search_text" placeholder="Rechercher" class="form-control" />
      </div>
    </div>
    <br />
    <div id="result"></div>
  </div>

  <script>
    $(document).ready(function() {

      load_data();

      function load_data(query) {
        $.ajax({
          url: "fetch.php",
          method: "POST",
          data: {
            query: query
          },
          success: function(data) {
            $('#result').html(data);
          }
        });
      }
      $('#search_text').keyup(function() {
        var search = $(this).val();
        if (search != '') {
          load_data(search);
        } else {
          load_data();
        }
      });
    });
  </script>
</body>

</html>