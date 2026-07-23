<?php

    $email = $_POST['email'];

    // build SELECT query
    $insert = "INSERT INTO `client_subscription` (`email`) VALUES ('$email');";
    // Connect to MySQL
    $pdo = new PDO('mysql:host=localhost; dbname=sehs3245', "root", "");
    // Set error handling mode so that any failure will throw an exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION );

    try {
    // insert Product
    $result = $pdo->exec($insert);
    if ($result)
    ?>
       <script language="javascript" type="text/javascript">
        //redirect to contact_us
        alert('Thank you for your subscription');
        history.back();;
        </script>

        <?php
            } catch (PDOException $e) {
            // Display error message
            echo $e->getMessage();
            }


?>