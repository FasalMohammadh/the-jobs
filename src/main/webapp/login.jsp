<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Dashboard - Manage JobSeekers</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="./assets/index.css" rel="stylesheet"/>
  <script src="./assets/index.js" type="module"></script>
  <script src="./assets/login.js" type="module"></script>
</head>
<body class="flex items-center justify-center min-h-screen">
<form
  action="authentication"
  id="loginForm"
  method="post" class="modal-box max-w-md w-full dark:bg-black/30">
  <h1 class="text-2xl font-medium mb-3 font-mont">Login</h1>

  <div class="grid gap-4">
    <input type="hidden" name="actionType" value="LOGIN"/>

    <div class="form-control w-full">
      <label class="label" for="email">
        <span class="label-text">Email</span>
      </label>
      <input inputmode="email" id="email" name="email" type="text" class="input input-bordered w-full"/>
      <label class="label">
        <span class="label-text-alt"></span>
      </label>
    </div>

    <div class="form-control w-full">
      <label class="label" for="password">
        <span class="label-text">Password</span>
      </label>
      <input id="password" name="password" type="password" class="input input-bordered w-full"/>
      <label class="label">
        <span class="label-text-alt"></span>
      </label>
    </div>

  </div>
  <div class="w-fit ml-auto grid grid-cols-2 gap-2">
    <button type="reset" class="btn btn-neutral">Clear</button>
    <button type="submit" class="btn btn-primary">Login</button>
  </div>
</form>

<script defer type="module">
  import Swal from 'https://cdn.jsdelivr.net/npm/sweetalert2@11.7.27/+esm';

  const feedback = "${feedback}";
  if (feedback) {
    document.addEventListener('DOMContentLoaded', () => {
      Swal.fire({
        title: 'Operation Failed.',
        text: feedback,
        icon: 'error',
        confirmButtonText: 'Close'
      }).then(()=>{
        window.location.replace("login.jsp")
      })
    })
  }
</script>
</body>
</html>
