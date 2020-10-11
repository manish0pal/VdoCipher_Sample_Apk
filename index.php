<?php
header('Content-Type: application/json');

$curl = curl_init();
$videoid = "b9b5102b996b4ab8b0869a1617bccf43";

curl_setopt_array($curl, array(
  CURLOPT_URL => "https://dev.vdocipher.com/api/videos/".$videoid."/otp",

  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => json_encode([
    "ttl" => 300,
  ]),
  CURLOPT_HTTPHEADER => array(
    "Accept: application/json",
    "Authorization: Apisecret Xe52glUTwjeZNRY9G5TJrvlYjzPZVuQX23NHLRNjY9gWjC1J60Uko9GgcEkhfYDc",
    "Content-Type: application/json"
  ),
));


$response = curl_exec($curl);

$err = curl_error($curl);
curl_close($curl);
if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;

}

?>