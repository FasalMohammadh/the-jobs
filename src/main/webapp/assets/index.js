window.addEventListener('DOMContentLoaded', () => {
  const htmlElement = document.querySelector('html');
  const theme = userPreferredTheme();
  htmlElement.setAttribute('data-theme', theme);
  if (theme === 'dark') htmlElement.classList.add('dark');
});

const themeTogglers = document.querySelectorAll('[data-theme="toggler"]');

document.addEventListener('DOMContentLoaded', () => {
  if (userPreferredTheme() === 'light') themeTogglers.forEach(item => (item.checked = true));
});

themeTogglers.forEach(item => item.addEventListener('click', toggleTheme));

function toggleTheme() {
  const htmlElement = document.querySelector('html');
  const currentTheme = htmlElement.getAttribute('data-theme');

  const themeToSwitch = currentTheme === 'dark' ? 'light' : 'dark';
  htmlElement.setAttribute('data-theme', themeToSwitch);
  localStorage.setItem('theme', themeToSwitch);
  htmlElement.classList.toggle('dark');
}

function userPreferredTheme() {
  const highPriorityTheme = localStorage.getItem('theme');
  if (highPriorityTheme) return highPriorityTheme;

  return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
}

function extractDataFromForm(form) {
  const formData = new FormData(form);
  const data = {};
  formData.forEach((value, key) => {
    data[key] = value;
  });
  return data;
}

function validateForm(schema, formHelperTextSelector) {
  const formHelperTextSelectorDefault =
    formHelperTextSelector || (node => node.parentElement.querySelector('.label-text-alt'));

  return function (event) {
    try {
      const data = extractDataFromForm(event.target);
      clearErrors(event.target, data);
      schema.parse(data);
    } catch (error) {
      event.preventDefault();
      const errors = error.formErrors.fieldErrors;
      Object.entries(errors).forEach(([key, errors]) => {
        const errorElement = event.target.querySelector(`[name="${key}"]`);
        errorElement.classList.add('input-error');
        const helperText = formHelperTextSelectorDefault(errorElement);
        helperText.textContent = errors[0];
        helperText.classList.add('text-error');
      });
    }
  };
}

function clearErrors(form, data, formHelperTextSelector) {
  const formHelperTextSelectorDefault =
    formHelperTextSelector || (node => node.parentElement.querySelector('.label-text-alt'));

  Object.keys(data).forEach(key => {
    const input = form.querySelector(`[name="${key}"]`);
    console.log(input);
    input.classList.remove('input-error');
    const helperText = formHelperTextSelectorDefault(input);
    helperText.textContent = '';
    helperText.classList.remove('text-error');
  });
}

function parseJson(str) {
  return JSON.parse(str.replaceAll("&quot;", `"`))
}

const countries = ([
  'India',
  'China',
  'United States',
  'Indonesia',
  'Pakistan',
  'Nigeria',
  'Brazil',
  'Bangladesh',
  'Russia',
  'Mexico',
  'Ethiopia',
  'Japan',
  'Philippines',
  'Egypt',
  'DR Congo',
  'Vietnam',
  'Iran',
  'Turkey',
  'Germany',
  'Thailand',
  'United Kingdom',
  'Tanzania',
  'France',
  'South Africa',
  'Italy',
  'Kenya',
  'Myanmar',
  'Colombia',
  'South Korea',
  'Uganda',
  'Sudan',
  'Spain',
  'Argentina',
  'Algeria',
  'Iraq',
  'Afghanistan',
  'Poland',
  'Canada',
  'Morocco',
  'Saudi Arabia',
  'Ukraine',
  'Angola',
  'Uzbekistan',
  'Yemen',
  'Peru',
  'Malaysia',
  'Ghana',
  'Mozambique',
  'Nepal',
  'Madagascar',
  "Côte d'Ivoire",
  'Venezuela',
  'Cameroon',
  'Niger',
  'Australia',
  'North Korea',
  'Mali',
  'Burkina Faso',
  'Syria',
  'Sri Lanka',
  'Malawi',
  'Zambia',
  'Romania',
  'Chile',
  'Kazakhstan',
  'Chad',
  'Ecuador',
  'Somalia',
  'Guatemala',
  'Senegal',
  'Netherlands',
  'Cambodia',
  'Zimbabwe',
  'Guinea',
  'Rwanda',
  'Benin',
  'Burundi',
  'Tunisia',
  'Bolivia',
  'Haiti',
  'Belgium',
  'Jordan',
  'Dominican Republic',
  'Cuba',
  'South Sudan',
  'Sweden',
  'Honduras',
  'Czech Republic (Czechia)',
  'Azerbaijan',
  'Greece',
  'Papua New Guinea',
  'Portugal',
  'Hungary',
  'Tajikistan',
  'United Arab Emirates',
  'Belarus',
  'Israel',
  'Togo',
  'Austria',
  'Switzerland',
  'Sierra Leone',
  'Laos',
  'Serbia',
  'Nicaragua',
  'Libya',
  'Paraguay',
  'Kyrgyzstan',
  'Bulgaria',
  'Turkmenistan',
  'El Salvador',
  'Congo',
  'Singapore',
  'Denmark',
  'Slovakia',
  'Central African Republic',
  'Finland',
  'Norway',
  'Liberia',
  'State of Palestine',
  'Lebanon',
  'New Zealand',
  'Costa Rica',
  'Ireland',
  'Mauritania',
  'Oman',
  'Panama',
  'Kuwait',
  'Croatia',
  'Eritrea',
  'Georgia',
  'Mongolia',
  'Moldova',
  'Uruguay',
  'Bosnia and Herzegovina',
  'Albania',
  'Jamaica',
  'Armenia',
  'Gambia',
  'Lithuania',
  'Qatar',
  'Botswana',
  'Namibia',
  'Gabon',
  'Lesotho',
  'Guinea-Bissau',
  'Slovenia',
  'North Macedonia',
  'Latvia',
  'Equatorial Guinea',
  'Trinidad and Tobago',
  'Bahrain',
  'Timor-Leste',
  'Estonia',
  'Mauritius',
  'Cyprus',
  'Eswatini',
  'Djibouti',
  'Fiji',
  'Comoros',
  'Guyana',
  'Bhutan',
  'Solomon Islands',
  'Luxembourg',
  'Montenegro',
  'Suriname',
  'Cabo Verde',
  'Micronesia',
  'Malta',
  'Maldives',
  'Brunei',
  'Bahamas',
  'Belize',
  'Iceland',
  'Vanuatu',
  'Barbados',
  'Sao Tome & Principe',
  'Samoa',
  'Saint Lucia',
  'Kiribati',
  'Grenada',
  'Tonga',
  'Seychelles',
  'St. Vincent & Grenadines',
  'Antigua and Barbuda',
  'Andorra',
  'Dominica',
  'Saint Kitts & Nevis',
  'Marshall Islands',
  'Liechtenstein',
  'Monaco',
  'San Marino',
  'Palau',
  'Nauru',
  'Tuvalu',
  'Holy See',
]);

export {parseJson, countries, userPreferredTheme, toggleTheme, extractDataFromForm, validateForm, clearErrors};
