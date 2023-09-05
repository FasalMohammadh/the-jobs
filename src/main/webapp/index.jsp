<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>

<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Home</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="./assets/index.css" rel="stylesheet"/>
  <script src="./assets/index.js" type="module"></script>
</head>
<body class="min-h-screen flex flex-col">

<header>
  <div class="navbar bg-base-300 px-5">
    <div class="flex-1">
      <h1 class="font-bold text-lg lg:text-3xl text-primary">
        The Jobs<span class="text-secondary">.</span>
      </h1>
    </div>
    <div class="flex-none gap-2">
      <ul class="mr-5 join">
        <a href="home" class="join-item btn btn-primary btn-outline px-10 btn-active">
          Home
        </a>
        <a href="login.jsp" class="join-item btn btn-primary btn-outline px-10">
          Login
        </a>
      </ul>

      <label class="swap btn btn-circle" aria-label="theme toggler">
        <input type="checkbox" data-theme="toggler"/>
        <i class="bi bi-moon text-xl swap-off"></i>
        <i class="bi bi-sun text-xl swap-on"></i>
      </label>

    </div>
  </div>
</header>

<main class="flex items-center justify-center mt-10">
  <tag:if test="${consultants==null}">
    <h1 colspan="7" class="text-center text-xl">No consultants found.</h1>
  </tag:if>
  <tag:if test="${consultants!=null}">
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 max-w-6xl gap-3">
      <tag:forEach var="consultant" items="${consultants}">
        <div class="max-w-lg bg-base-200 rounded-lg shadow-md p-5 w-full">
          <h2 class="text-center text-2xl font-semibold mt-3">${consultant.firstName} ${consultant.lastName}</h2>
          <p class="text-center text-gray-600 mt-1">Specialized: ${consultant.job}, ${consultant.country}</p>

          <div class="mt-5">
            <h3 class="text-xl font-semibold mb-2">Availability</h3>

            <ul class="flex flex-col gap-1">
              <tag:forEach var="availability" items="${consultant.availability}" varStatus="loop">
                <li class="flex items-center gap-3">
                  <p class="text-center">${availability.day}</p>
                  <p class="text-center whitespace-nowrap">${availability.startTime} - ${availability.endTime}</p>
                </li>
              </tag:forEach>
            </ul>
          </div>
        </div>

      </tag:forEach>
    </div>
  </tag:if>

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
        window.location.replace("/home")
      })
    })
  }
</script>
</body>
</html>
