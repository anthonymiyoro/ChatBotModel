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
            'timeout'  => 200.0,
        ]);

        $res = $client->request('POST', 'http://amiyoro2.pythonanywhere.com/analyse_tweet/', [
            'form_params' => [
                'twitter_user' => $tname,
            ]
        ]);

        $result= $res->getBody();
        dd($result);

    }
}
