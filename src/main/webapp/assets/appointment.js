
'use strict';
import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
import {
  clearErrors,
  extractDataFromForm,
  validateForm,
  countries, parseJson
} from './index.js';

document.getElementById('countryList').innerHTML = countries
  .map(country => `<option>${country}</option>`)
  .join(' ');


// consultant form
const schema = z.object({
  date: z.string().trim().nonempty('Date is required.'),
  time: z.string().trim().nonempty('Time is required.'),
  job: z.string().trim().nonempty('Job is required.'),
  country: z.enum(countries, {
    errorMap: (_issue, ctx) => {
      if (ctx.data === '') return {message: 'Country is required.'};
      return {message: 'Invalid country.'};
    },
  }),
  jobSeeker: z.string().trim().nonempty('Job seeker is required.'),
  consultant: z.string().trim().nonempty('Consultant is required.'),
  status: z.enum(['COMPLETED', 'CREATED', 'CANCELLED'], {
    errorMap: (_issue, ctx) => {
      if (ctx.data === '') return {message: 'Status is required.'};
      return {message: 'Invalid Status.'};
    },
  }),
});

const manageAppointmentForm = document.querySelector("#appointmentForm");
manageAppointmentForm.addEventListener('submit', validateForm(schema));
manageAppointmentForm.addEventListener('reset', event =>
  clearErrors(event.target, extractDataFromForm(event.target))
);

const manageJobSeekerFormDialog = document.getElementById('appointmentDialog');
manageJobSeekerFormDialog.addEventListener('close', () => {
  manageAppointmentForm.reset();

  manageAppointmentForm.id.value = "";
  manageAppointmentForm.date.value = "";
  manageAppointmentForm.job.value = "";
  manageAppointmentForm.country.value = "";
  manageAppointmentForm.consultant.value = "";
  manageAppointmentForm.jobSeeker.value = "";

  manageAppointmentForm.actionType.value = 'CREATE';

  manageAppointmentForm.status.selectedIndex = 0;
  manageAppointmentForm.status.classList.add("select-disabled", "pointer-events-none");
  manageAppointmentForm.status.setAttribute("onclick", "return false");
  manageAppointmentForm.status.setAttribute("onkeydown", "return false");
});

document.getElementById('create-job-seeker')?.addEventListener('click', () => {
  manageJobSeekerFormDialog.showModal();
});

document.querySelectorAll('[data-action="edit"]').forEach(edit => {
  edit.addEventListener('click', () => {
    manageAppointmentForm.status.classList.remove("select-disabled", "pointer-events-none");
    manageAppointmentForm.status.removeAttribute("onclick");
    manageAppointmentForm.status.removeAttribute("onkeydown");


    manageJobSeekerFormDialog.showModal();
    const record = parseJson(edit.dataset.record.replaceAll("&quot;", '"'));

    manageAppointmentForm.id.value = record.id;
    manageAppointmentForm.date.value = record.dateTime.split("T").shift();
    manageAppointmentForm.time.value = record.dateTime.split("T").pop();
    manageAppointmentForm.job.value = record.job;
    manageAppointmentForm.country.value = record.country;
    manageAppointmentForm.consultant.value = record.consultantId;
    manageAppointmentForm.jobSeeker.value = record.jobSeekerId;

    manageAppointmentForm.actionType.value = 'UPDATE';
  });
});

const deleteForm = document.querySelector("#appointment-delete-form")
document.querySelectorAll('[data-action="delete"]').forEach(button => {
  button.addEventListener('click', () => {
    confirmationDialog.showModal();
    const record = JSON.parse(button.dataset.record);

    deleteForm.id.value = record.id;
    deleteForm.querySelector('#displayId').textContent = record.id;
  });
});
