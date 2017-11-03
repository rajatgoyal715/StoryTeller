<?php
require('simple_html_dom.php');
$url = $_GET['tag'];
try {
	$html = file_get_html($url);

	$i = 0;
	foreach($html->find('.u-xs-size32x32') as $element){
			if($i==5) break;
	       	$pic[] = $element->src;
	       	$i++;
	}


	foreach($html->find('a[data-action=show-user-card]') as $element) {

			if( strpos( $element->innertext, '<img' ) === false ) {
		       $author[] = $element->innertext;
			}

	}

	$i = 0;
	foreach($html->find('time[datetime]') as $element) {
			if($i==5) break;
	       	$date[] = $element->innertext;
	       	$i++;
	}

	$i = 0;
	foreach($html->find('h3.graf--title') as $element) {
			if($i==5) break;
	       	$head[] = $element->plaintext;
	       	$i++;
	}


	$content = "";
	$photos = "";
	$i = 0;
	foreach($html->find('.postArticle-readMore a[data-action-source]') as $element) {
			if($i==5) break;
	        $link[] = $element->href;
	        $variable = file_get_html($element->href);
	        foreach ($variable->find('p.graf') as $key) {
	       		$content = $content.$key->plaintext;
	        }

			foreach($variable->find('img[data-image-id]') as $key) {
			       $photos = $key->src;
			}

			if($photos == "")
				$photo[] = NULL;
			else
				$photo[] = $photos;

	       	$contents[] = str_replace("*", "", $content);
	       	$i++;
	}

	$finalJSONArray = array();

	for($l = 0; $l < 5; $l++) {
		$js = array("link" => $link[$l], "pic" => $pic[$l], "author" => $author[$l], "date" => $date[$l], "photo" => $photo[$l], "head" => $head[$l], "contents" => $contents[$l]);
		$finalJSONArray[] = $js;
	}

	$fp = fopen('Python.json', 'w');
	fwrite($fp, json_encode($finalJSONArray));
	fclose($fp);

}
catch(Exception $e) {
	echo 'Message: ' .$e->getMessage();
}


?>