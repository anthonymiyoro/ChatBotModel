<html>
    <h1>FORM</h1> <p>ni gani?</p>
    <form method="POST" action="{{URL::to('/submit')}}">
    <input name="Twitter Username" type="text">
    <input name="Token" value={{csrf_token()}} type="hidden">
    <button type="submit">submit</button>
    </form>
</html>
