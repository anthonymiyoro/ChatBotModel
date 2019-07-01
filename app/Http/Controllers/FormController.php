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
        $tname = ($request->input('Twitter_Username'));

           $client = new Client([
          // You can set any number of default request options.
          'timeout'  => 2000.0,
          'auth' => ['admin','Pass@1234']
      ]);

      $request = $client->post('http://amiyoro2.pythonanywhere.com/analyse_tweet/', [
            'form_params' => ['twitter_user' => '@migunamiguna'],
            'twitter_user' => '@migunamiguna'
        ]);

        echo $request->getStatusCode();
        // "200"
        echo '<br/>';
        echo $request->getHeader('content-type')[0];
        echo '<br/>';
        echo $request->getBody();
    }
}
