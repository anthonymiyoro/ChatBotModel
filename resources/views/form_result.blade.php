<html>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <h1>FORM RESULT</h1>
    @foreach($apiResult as $tweet)
    <p>{{$tweet}}</p>
    @endforeach
</html>
