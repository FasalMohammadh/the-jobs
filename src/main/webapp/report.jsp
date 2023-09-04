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
</head>
<body>
<div class="drawer">
  <input id="my-drawer-3" type="checkbox" class="drawer-toggle"/>
  <div class="drawer-content grid grid-flow-row lg:grid-cols-[auto_1fr]">
    <div class="navbar w-fit items-start bg-base-300 lg:min-h-screen lg:h-full rounded lg:rounded-none">
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
      <main class="flex flex-col gap-3">
        <section class="block w-fit ml-auto">
          <form method="post" action="report" class="grid gap-3 items-end"
                style="grid-template-columns: repeat(2,minmax(200px,1fr)) auto">

            <div class="form-control w-full max-w-xs">
              <label class="label" for="month">
                <span class="label-text">Year & Month</span>
              </label>
              <input required type="month" name="yearMonth" id="month" class="input input-bordered"/>
            </div>

            <button id="create-job-seeker" class="btn btn-primary normal-case">
              Generate Report
            </button>

          </form>
        </section>
        <tag:if
          test="${appointments!=null && mostAppointedConsultant!=null && totalAppointments!=null && mostAppointedJob!=null && mostAppointedCountry!=null}">

          <section class="w-full">


            <header class="px-5 py-4">
              <h2 class="font-semibold text-xl">Report</h2>
            </header>

            <div class="overflow-x-auto rounded-lg mx-auto bg-base-200">
              <table class="table table-zebra" aria-label="report">
                <thead>
                <tr>
                  <th>Id</th>
                  <th>Date Time</th>
                  <th>Job</th>
                  <th>Country</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tag:if test="${appointments==null}">
                  <tr>
                    <td colspan="7" class="text-center">No records found.</td>
                  </tr>
                </tag:if>
                <tag:if test="${appointments!=null}">
                  <tag:forEach var="appointment" items="${appointments}">
                    <tr class="whitespace-nowrap">
                      <td>${appointment.id}</td>
                      <td>${appointment.dateTime}</td>
                      <td>${appointment.job}</td>
                      <td>${appointment.country}</td>
                      <td>${appointment.status}</td>
                    </tr>
                  </tag:forEach>
                </tag:if>


                <tr>
                  <td colspan="5" class="text-right font-bold">
                    Most appointed Job
                  </td>
                  <td colspan="1">
                      ${mostAppointedJob}
                  </td>
                </tr>

                <tr>
                  <td colspan="5" class="text-right font-bold">
                    Most appointed country
                  </td>
                  <td colspan="1">
                      ${mostAppointedCountry}
                  </td>
                </tr>
                </tbody>
              </table>

            </div>
          </section>

          <section class="w-full">

            <header class="px-5 py-4">
              <h2 class="text-lg font-semibold">Most appointed consultant</h2>
            </header>

            <div class="overflow-x-auto rounded-lg mx-auto bg-base-200">

              <table class="table table-zebra" aria-label="report">
                <thead class="whitespace-nowrap">
                <tr>
                  <th>Id</th>
                  <th>Full Name</th>
                  <th>Email</th>
                  <th>Phone Number</th>
                  <th>Country</th>
                  <th>Job</th>
                </tr>
                </thead>
                <tbody class="whitespace-nowrap">
                <tr>
                  <td>${mostAppointedConsultant.id}</td>
                  <td>${mostAppointedConsultant.firstName} ${mostAppointedConsultant.lastName}</td>
                  <td>${mostAppointedConsultant.email}</td>
                  <td>${mostAppointedConsultant.phoneNumber}</td>
                  <td>${mostAppointedConsultant.country}</td>
                  <td>${mostAppointedConsultant.job}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </section>

        </tag:if>

        <tag:if
          test="${appointments==null && mostAppointedConsultant==null && totalAppointments==null && mostAppointedJob==null && mostAppointedCountry==null}">
          <p class="text-2xl font-bold mt-3">Please click on "Generate Report" button to generate an instant report</p>
        </tag:if>

      </main>

    </div>
  </div>

  <aside class="drawer-side">
    <label for="my-drawer-3" class="drawer-overlay"></label>
    <div class="bg-base-300 min-h-screen h-full">
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
