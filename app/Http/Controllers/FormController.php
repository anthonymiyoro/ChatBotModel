<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesResources;

use Illuminate\Http\Request;
use GuzzleHttp\Client;


class FormController extends BaseController
{
    public function show(Request $request){
        return view('greeting');
    }

    public function sendPostRequest(Request $request){

        print_r($request->input('Twitter_Username'));
        echo '<br/>';
        $form_result = ($request->input('Twitter_Username'));

        // Pass input from form to avoid expected POST request but got GET request error
        $twitter_username = (string)$form_result;

        $client = new Client([

        // Pass long timeout because server is slow :(
        'timeout'  => 2000.0,
        'auth' => ['admin','Pass@1234']
      ]);

      $request = $client->post('http://amiyoro2.pythonanywhere.com/analyse_tweet/', [
            'form_params' => ['twitter_user' => $twitter_username],
            'twitter_user' => $twitter_username
        ]);

        echo $request->getStatusCode();
        // "200"
        echo '<br/>';
        echo $request->getHeader('content-type')[0];
        echo '<br/>';
        echo $request->getBody();
    }
}
