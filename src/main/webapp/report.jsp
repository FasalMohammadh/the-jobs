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
  <script src="./assets/jobSeeker.js" type="module"></script>
</head>
<body>
<div class="drawer">
  <input id="my-drawer-3" type="checkbox" class="drawer-toggle"/>
  <div class="drawer-content grid grid-flow-row lg:grid-cols-[auto_1fr]">
    <div class="navbar w-fit items-start bg-base-300 h-fit">
      <div class="flex-none lg:hidden">
        <label for="my-drawer-3" class="btn btn-square btn-ghost">
          <i class="bi bi-list text-2xl"></i>
        </label>
      </div>
      <div class="flex-none hidden lg:block min-h-screen">
        <div class="flex justify-between px-3 mb-3">
          <div>
            <h1 class="font-bold text-lg lg:text-3xl text-primary">
              The Jobs<span class="text-secondary">.</span>
            </h1>
            <p class="text-sm dark:text-slate-500 mb-2">Welcome back,</p>
          </div>

          <label class="swap btn btn-circle" aria-label="theme toggler">
            <input type="checkbox" data-theme="toggler"/>
            <i class="bi bi-moon text-xl swap-off"></i>
            <i class="bi bi-sun text-xl swap-on"></i>
          </label>
        </div>

        <ul class="menu menu-vertical font-poppins gap-2">
          <li>
            <a href="index.html" class="group">
              <i class="bi bi-house group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <p class="text-base-content leading-4 group-hover:text-primary">Home</p>
            </a>
          </li>
          <li>
            <a href="appointment" class="group">
              <i class="bi bi-file-earmark group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <div>
                <p class="text-base-content leading-4 group-hover:text-indigo-600">Appointments</p>
                <span class="text-sm dark:text-slate-500">Manage Appointments</span>
              </div>
            </a>
          </li>
          <li>
            <a href="report.jsp" class="active">
              <i class="bi bi-file-medical text-primary-focus text-2xl w-8 h-8"></i>
              <div>
                <p class="leading-4 text-primary">Reports</p>
                <span class="text-sm dark:text-slate-500">Generate Reports</span>
              </div>
            </a>
          </li>
          <li>
            <a href="consultant" class="group">
              <i class="bi bi-people group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <div>
                <p class="text-base-content leading-4 group-hover:text-indigo-600">
                  Consultants
                </p>
                <span class="text-sm dark:text-slate-500">Manage Consultants</span>
              </div>
            </a>
          </li>

          <li>
            <a href="job-seeker" class="group">
              <i class="bi bi-people group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <div>
                <p class="text-base-content leading-4 group-hover:text-indigo-600">Job Seekers</p>
                <span class="text-sm dark:text-slate-500">Manage Job Seekers</span>
              </div>
            </a>
          </li>
        </ul>
        <div class="divider m-2"></div>
        <ul class="menu menu-vertical gap-2">
          <li>
            <a href="setting.html" class="group">
              <i class="bi bi-gear group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <p class="text-base-content leading-4 group-hover:text-indigo-600">Settings</p>
            </a>
          </li>
          <li>
            <div>
              <i
                class="bi bi-box-arrow-right group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
              <p class="text-base-content leading-4 group-hover:text-indigo-600">Logout</p>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="p-10 min-w-0">
      <main>
        <section class="block w-fit ml-auto mb-3">
          <form action="report">

            <div class="form-control w-full max-w-xs">
              <label class="label" for="firstName">
                <span class="label-text">Month</span>
              </label>
              <input id="month" name="month" type="text" class="input input-bordered w-full max-w-xs"/>
              <label class="label">
                <span class="label-text-alt"></span>
              </label>
            </div>

            <div class="form-control w-full max-w-xs">
              <label class="label" for="lastName">
                <span class="label-text">Type</span>
              </label>
              <input id="type" name="type" type="text" class="input input-bordered w-full max-w-xs"/>
              <label class="label">
                <span class="label-text-alt"></span>
              </label>
            </div>

            <button id="create-job-seeker" class="btn btn-primary normal-case">
              Generate Report
            </button>

          </form>
        </section>
        <section class="w-full">
          <header class="px-5 py-4">
            <h2 class="font-semibold text-xl">Generated Report</h2>
          </header>

        </section>
      </main>
    </div>
  </div>

  <aside class="drawer-side">
    <label for="my-drawer-3" class="drawer-overlay"></label>
    <div class="bg-base-300 min-h-screen">
      <div class="flex justify-between px-3 mb-2 py-4">
        <div>
          <h1 class="font-bold text-lg lg:text-3xl text-primary">
            The Jobs<span class="text-secondary">.</span>
          </h1>
          <p class="text-sm dark:text-slate-500 mb-2">Welcome back,</p>
        </div>

        <label class="swap btn btn-circle" aria-label="theme-toggler">
          <input type="checkbox" data-theme="toggler"/>
          <i class="bi bi-moon text-xl swap-off"></i>
          <i class="bi bi-sun text-xl swap-on"></i>
        </label>
      </div>

      <ul class="menu menu-vertical font-poppins gap-2">
        <li>
          <a href="index.html" class="group">
            <i class="bi bi-house group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <p class="text-base-content leading-4 group-hover:text-primary">Home</p>
          </a>
        </li>
        <li>
          <a href="appointment" class="group">
            <i class="bi bi-file-earmark group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <div>
              <p class="text-base-content leading-4 group-hover:text-indigo-600">Appointments</p>
              <span class="text-sm dark:text-slate-500">Manage Appointments</span>
            </div>
          </a>
        </li>
        <li>
          <a href="report.jsp" class="active">
            <i class="bi bi-file-medical text-primary-focus text-2xl w-8 h-8"></i>
            <div>
              <p class="leading-4 text-primary">Reports</p>
              <span class="text-sm dark:text-slate-500">Generate Reports</span>
            </div>
          </a>
        </li>
        <li>
          <a href="consultant" class="group">
            <i class="bi bi-people group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <div>
              <p class="text-base-content leading-4 group-hover:text-indigo-600">Consultants</p>
              <span class="text-sm dark:text-slate-500">Manage Consultants</span>
            </div>
          </a>
        </li>

        <li>
          <a href="job-seeker" class="group">
            <i class="bi bi-people group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <div>
              <p class="text-base-content leading-4 group-hover:text-indigo-600">Job Seekers</p>
              <span class="text-sm dark:text-slate-500">Manage Job Seekers</span>
            </div>
          </a>
        </li>
      </ul>
      <div class="divider m-2"></div>
      <ul class="menu menu-vertical gap-2">
        <li>
          <a href="setting.html" class="group">
            <i class="bi bi-gear group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <p class="text-base-content leading-4 group-hover:text-indigo-600">Settings</p>
          </a>
        </li>
        <li>
          <div class="group">
            <i
              class="bi bi-box-arrow-right group-hover:text-indigo-600 text-2xl w-8 h-8"></i>
            <p class="text-base-content leading-4 group-hover:text-indigo-600">Logout</p>
          </div>
        </li>
      </ul>
    </div>
  </aside>
</div>


<dialog id="appointmentDialog" class="modal">
  <form
    action="job-seeker"
    class="modal-box"
    name="appointmentForm"
    id="appointmentForm"
    method="post">
    <h1 class="text-xl font-medium mb-3 font-mont">Job Seeker Details</h1>

    <div class="grid sm:grid-cols-2 gap-4">
      <input type="hidden" name="actionType" value="CREATE"/>
      <input type="hidden" name="id"/>

      <div class="form-control w-full max-w-xs">
        <label class="label" for="firstName">
          <span class="label-text">First name</span>
        </label>
        <input id="firstName" name="firstName" type="text" class="input input-bordered w-full max-w-xs"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

      <div class="form-control w-full max-w-xs">
        <label class="label" for="lastName">
          <span class="label-text">Last name</span>
        </label>
        <input id="lastName" name="lastName" type="text" class="input input-bordered w-full max-w-xs"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

      <div class="form-control w-full max-w-xs">
        <label class="label" for="email">
          <span class="label-text">Email</span>
        </label>
        <input id="email" name="email" type="text" class="input input-bordered w-full max-w-xs"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>

      <div class="form-control w-full max-w-xs">
        <label class="label" for="phoneNumber">
          <span class="label-text">Phone number</span>
        </label>
        <input id="phoneNumber" name="phoneNumber" type="text" class="input input-bordered w-full max-w-xs"/>
        <label class="label">
          <span class="label-text-alt"></span>
        </label>
      </div>
    </div>
    <div class="w-fit ml-auto grid grid-cols-2 gap-2">
      <button type="reset" class="btn btn-neutral">Clear</button>
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
  </form>
  <form method="dialog" class="modal-backdrop">
    <button>close</button>
  </form>
</dialog>

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
      })
    })
  }
</script>
</body>
</html>
