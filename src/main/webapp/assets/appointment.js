// 'use strict';
// import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
// import {
//     clearErrors,
//     extractDataFromForm,
//     validateForm,
// } from './index.js';
//
//
// // consultant form
//
// const countries = /** @type {const} */ ([
//     'India',
//     'China',
//     'United States',
//     'Indonesia',
//     'Pakistan',
//     'Nigeria',
//     'Brazil',
//     'Bangladesh',
//     'Russia',
//     'Mexico',
//     'Ethiopia',
//     'Japan',
//     'Philippines',
//     'Egypt',
//     'DR Congo',
//     'Vietnam',
//     'Iran',
//     'Turkey',
//     'Germany',
//     'Thailand',
//     'United Kingdom',
//     'Tanzania',
//     'France',
//     'South Africa',
//     'Italy',
//     'Kenya',
//     'Myanmar',
//     'Colombia',
//     'South Korea',
//     'Uganda',
//     'Sudan',
//     'Spain',
//     'Argentina',
//     'Algeria',
//     'Iraq',
//     'Afghanistan',
//     'Poland',
//     'Canada',
//     'Morocco',
//     'Saudi Arabia',
//     'Ukraine',
//     'Angola',
//     'Uzbekistan',
//     'Yemen',
//     'Peru',
//     'Malaysia',
//     'Ghana',
//     'Mozambique',
//     'Nepal',
//     'Madagascar',
//     "Côte d'Ivoire",
//     'Venezuela',
//     'Cameroon',
//     'Niger',
//     'Australia',
//     'North Korea',
//     'Mali',
//     'Burkina Faso',
//     'Syria',
//     'Sri Lanka',
//     'Malawi',
//     'Zambia',
//     'Romania',
//     'Chile',
//     'Kazakhstan',
//     'Chad',
//     'Ecuador',
//     'Somalia',
//     'Guatemala',
//     'Senegal',
//     'Netherlands',
//     'Cambodia',
//     'Zimbabwe',
//     'Guinea',
//     'Rwanda',
//     'Benin',
//     'Burundi',
//     'Tunisia',
//     'Bolivia',
//     'Haiti',
//     'Belgium',
//     'Jordan',
//     'Dominican Republic',
//     'Cuba',
//     'South Sudan',
//     'Sweden',
//     'Honduras',
//     'Czech Republic (Czechia)',
//     'Azerbaijan',
//     'Greece',
//     'Papua New Guinea',
//     'Portugal',
//     'Hungary',
//     'Tajikistan',
//     'United Arab Emirates',
//     'Belarus',
//     'Israel',
//     'Togo',
//     'Austria',
//     'Switzerland',
//     'Sierra Leone',
//     'Laos',
//     'Serbia',
//     'Nicaragua',
//     'Libya',
//     'Paraguay',
//     'Kyrgyzstan',
//     'Bulgaria',
//     'Turkmenistan',
//     'El Salvador',
//     'Congo',
//     'Singapore',
//     'Denmark',
//     'Slovakia',
//     'Central African Republic',
//     'Finland',
//     'Norway',
//     'Liberia',
//     'State of Palestine',
//     'Lebanon',
//     'New Zealand',
//     'Costa Rica',
//     'Ireland',
//     'Mauritania',
//     'Oman',
//     'Panama',
//     'Kuwait',
//     'Croatia',
//     'Eritrea',
//     'Georgia',
//     'Mongolia',
//     'Moldova',
//     'Uruguay',
//     'Bosnia and Herzegovina',
//     'Albania',
//     'Jamaica',
//     'Armenia',
//     'Gambia',
//     'Lithuania',
//     'Qatar',
//     'Botswana',
//     'Namibia',
//     'Gabon',
//     'Lesotho',
//     'Guinea-Bissau',
//     'Slovenia',
//     'North Macedonia',
//     'Latvia',
//     'Equatorial Guinea',
//     'Trinidad and Tobago',
//     'Bahrain',
//     'Timor-Leste',
//     'Estonia',
//     'Mauritius',
//     'Cyprus',
//     'Eswatini',
//     'Djibouti',
//     'Fiji',
//     'Comoros',
//     'Guyana',
//     'Bhutan',
//     'Solomon Islands',
//     'Luxembourg',
//     'Montenegro',
//     'Suriname',
//     'Cabo Verde',
//     'Micronesia',
//     'Malta',
//     'Maldives',
//     'Brunei',
//     'Bahamas',
//     'Belize',
//     'Iceland',
//     'Vanuatu',
//     'Barbados',
//     'Sao Tome & Principe',
//     'Samoa',
//     'Saint Lucia',
//     'Kiribati',
//     'Grenada',
//     'Tonga',
//     'Seychelles',
//     'St. Vincent & Grenadines',
//     'Antigua and Barbuda',
//     'Andorra',
//     'Dominica',
//     'Saint Kitts & Nevis',
//     'Marshall Islands',
//     'Liechtenstein',
//     'Monaco',
//     'San Marino',
//     'Palau',
//     'Nauru',
//     'Tuvalu',
//     'Holy See',
// ]);
//
// document.getElementById('countryList').innerHTML = countries
//     .map(country => `<option>${country}</option>`)
//     .join(' ');
//
// const schema = z.object({
//     date: z.string().trim().nonempty('Date is required.'),
//     time: z.string().trim().nonempty('Time is required.'),
//     job: z.string().trim().nonempty('Job is required.'),
//     country: z.enum(countries, {
//         errorMap: (_issue, ctx) => {
//             if (ctx.data === '') return {message: 'Country is required.'};
//             return {message: 'Invalid country.'};
//         },
//     }),
//     jobSeeker: z.string().trim().nonempty('Job seeker is required.'),
//     consultant: z.string().trim().nonempty('Consultant is required.'),
//     status: z.enum(['COMPLETED', 'CREATED', 'CANCELLED'], {
//         errorMap: (_issue, ctx) => {
//             if (ctx.data === '') return {message: 'Status is required.'};
//             return {message: 'Invalid Status.'};
//         },
//     }),
// });
//
// const manageAppointmentForm = document.appointmentForm;
// const deleteAppointmentForm = document.appointmentDeleteForm;
// manageAppointmentForm.addEventListener('submit', validateForm(schema));
// manageAppointmentForm.addEventListener('reset', event =>
//     clearErrors(event.target, extractDataFromForm(event.target))
// );
//
// const manageAppointmentFormDialog = document.getElementById('appointmentDialog');
// manageAppointmentFormDialog.addEventListener('close', () => {
//     manageAppointmentForm.reset();
//
//     manageAppointmentForm.id.value = '';
//     manageAppointmentForm.date.value = '';
//     manageAppointmentForm.time.value = '';
//     manageAppointmentForm.job.value = '';
//     manageAppointmentForm.country.value = '';
//     manageAppointmentForm.status.value = '';
//     manageAppointmentForm.actionType.value = 'CREATE';
//
//     manageAppointmentForm.status.setAttribute('readonly', 'true');
//     manageAppointmentForm.status.classList.add('select-disabled');
//     manageAppointmentForm.status.selectedIndex = 0;
// });
//
// document.getElementById('schedule-appointment')?.addEventListener('click', () => {
//     manageAppointmentFormDialog.showModal();
// });
//
// document.querySelectorAll('[data-action="edit"]').forEach(edit => {
//     edit.addEventListener('click', () => {
//         manageAppointmentFormDialog.showModal();
//         const record = JSON.parse(edit.dataset.record);
//
//         manageAppointmentForm.id.value = record.id;
//         manageAppointmentForm.date.value = record.date;
//         manageAppointmentForm.time.value = record.time;
//         manageAppointmentForm.job.value = record.job;
//         manageAppointmentForm.country.value = record.country;
//         manageAppointmentForm.status.value = record.status;
//         manageAppointmentForm.actionType.value = 'UPDATE';
//
//         manageAppointmentForm.status?.removeAttribute('readonly');
//         manageAppointmentForm.status.classList.remove('select-disabled');
//     });
// });
//
// document.querySelectorAll('[data-action="delete"]').forEach(button => {
//     button.addEventListener('click', () => {
//         confirmationDialog.showModal();
//         const record = JSON.parse(button.dataset.record);
//
//         deleteAppointmentForm.id.value = record.id;
//         deleteAppointmentForm.querySelector('#displayId').textContent = record.id;
//     });
// });


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
