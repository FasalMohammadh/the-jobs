/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ['./view/**/*.{html,js}'],
    darkMode: 'class',
    theme: {
        extend: {
            fontFamily: {
                mont: ['var(--font-mont)'],
                poppins: ['var(--font-poppins)'],
                'play-fair': ['var(--font-play-fair)'],
            },
        },
    },
    plugins: [require('daisyui')],
};
