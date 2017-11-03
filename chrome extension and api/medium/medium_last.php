<?php
include('simple_html_dom.php');
try {
	$html = file_get_html('https://medium.com/tag/Design');

	$i = 0;
	foreach($html->find('.u-xs-size32x32') as $element){
			if($i==5) break;
	       	$pic[] = $element->src;
	       	$i++;
	}

	//$i = 0;
	foreach($html->find('a[data-action=show-user-card]') as $element) {
		//if($i==5) break;
		//else {
			if( strpos( $element->innertext, '<img' ) === false ) {
		       $author[] = $element->innertext;
			}
			//$i++;
		//}
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
	       	$head[] = $element->innertext;
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

	

	// var_dump($link);
	// var_dump($pic);
	// var_dump($author);
	// var_dump($date);
	// var_dump($photo);
	// var_dump($head);
	// var_dump($contents);

	$finalJSONArray = array();

	for($l = 0; $l < 5; $l++) {
		$js = array("link" => $link, "pic" => $pic, "author" => $author, "date" => $date, "photo" => $photo, "head" => $head, "contents" => $contents);
		$finalJSONArray[] = $js;
	}

	echo json_encode($finalJSONArray);


}
catch(Exception $e) {
	echo 'Message: ' .$e->getMessage();
}


?>