<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Login</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="./assets/index.css" rel="stylesheet"/>
  <script src="./assets/index.js" type="module"></script>
  <script src="./assets/register.js" type="module"></script>
</head>
<body class="min-h-screen flex flex-col">

<header>
  <div class="navbar bg-base-300 px-5">
    <div class="flex-1">
      <h1 class="font-bold text-lg lg:text-3xl text-primary">
        The Jobs<span class="text-secondary">.</span>
      </h1>
    </div>
    <ul class="mr-5 join">
      <a href="home" class="hidden md:flex join-item btn btn-primary btn-outline px-10">
        Home
      </a>
      <a href="home" class="md:hidden join-item btn btn-circle">
        <i class=" bi bi-house"></i>
      </a>

      <a href="login.jsp" class="hidden md:flex join-item btn btn-primary btn-outline px-10">
        Login
      </a>
      <a href="login.jsp" class="md:hidden join-item btn btn-circle">
        <i class="bi bi-box-arrow-in-right"></i>
      </a>

      <a href="register.jsp" class="hidden md:flex join-item btn btn-primary btn-outline px-10 btn-active">
        Register
      </a>
      <a href="register.jsp" class="md:hidden join-item btn btn-circle btn-active">
        <i class="bi bi-person-add"></i>
      </a>
    </ul>

      <label class="swap btn btn-circle" aria-label="theme toggler">
        <input type="checkbox" data-theme="toggler"/>
        <i class="bi bi-moon text-xl swap-off"></i>
        <i class="bi bi-sun text-xl swap-on"></i>
      </label>

      <div class="tooltip tooltip-left" data-tip="Employee">
        <a href="employee-login.jsp" class="join-item btn btn-circle px-10">
          <i class="bi bi-person-lock text-xl"></i>
        </a>
      </div>

    </div>
  </div>
</header>

<main class="flex items-center justify-center flex-1">
  <form
    action="job-seeker"
    id="register-form"
    method="post" class="rounded-xl shadow-xl p-8 max-w-xl w-full bg-base-300">
    <h1 class="text-2xl font-medium mb-3 font-mont">Register</h1>

    <div class="grid gap-3 md:grid-cols-2">
      <input type="hidden" name="actionType" value="REGISTER"/>

      <div class="form-control w-full">
        <label class="label" for="firstName">
          <span class="label-text">First Name</span>
        </label>
        <input id="firstName" name="firstName" type="text" class="input input-bordered w-full"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

      <div class="form-control w-full">
        <label class="label" for="lastName">
          <span class="label-text">LastName</span>
        </label>
        <input id="lastName" name="lastName" type="text" class="input input-bordered w-full"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

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
        <label class="label" for="phoneNumber">
          <span class="label-text">Phone number</span>
        </label>
        <input inputmode="tel" id="phoneNumber" name="phoneNumber" type="tel" class="input input-bordered w-full"/>
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

      <div class="form-control w-full">
        <label class="label" for="confirmPassword">
          <span class="label-text">Confirm password</span>
        </label>
        <input id="confirmPassword" name="confirmPassword" type="password" class="input input-bordered w-full"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

    </div>
    <div class="w-fit ml-auto grid grid-cols-2 gap-2">
      <button type="reset" class="btn btn-neutral">Clear</button>
      <button type="submit" class="btn btn-primary">Register</button>
    </div>
  </form>
</main>


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
      }).then(() => {
        window.location.replace("login.jsp")
      })
    })
  }
</script>
</body>
</html>
