<?php
//fetch.php
$connect = mysqli_connect("db5000109013.hosting-data.io", "dbu324224", "Jdebla123?", "dbs103504");
$output = '';
if(isset($_POST["query"]))
{
 $search = mysqli_real_escape_string($connect, $_POST["query"]);
 $query = "
  SELECT * FROM web 
  WHERE id LIKE '%".$search."%' 
  OR expediteur LIKE '%".$search."%' 
  OR date LIKE '%".$search."%' 
  OR mail LIKE '%".$search."%' 
  OR texte LIKE '%".$search."%'
 ";
}
else
{
 $query = "
  SELECT * FROM web 
 ";
}
$result = mysqli_query($connect, $query);
if(mysqli_num_rows($result) > 0)
{
 $output .= '
  <div class="table-responsive">
   <table class="table table bordered">
    <tr>
     <th>Id</th>     
	 <th>Expéditeur</th>
     <th>Date</th>
	 <th>Mail</th>
     <th>Message</th>
    </tr>
 ';
 while($row = mysqli_fetch_array($result))
 {
  $output .= '
   <tr>
    <td>'.$row["id"].'</td>    
	<td>'.$row["expediteur"].'</td>
    <td>'.$row["date"].'</td>
	<td>'.$row["mail"].'</td>
    <td>'.$row["texte"].'</td>
   </tr>
  ';
 }
 echo $output;
}
else
{
 echo 'Aucun message trouvé';
}

?>