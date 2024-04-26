/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.html"],
  theme: {
    extend: {
      width: {
        642: "542px",
      },
      height: {
        110: "110px",
      },
      padding: {
        5: "0.345rem",
      },
    },
  },
  plugins: [],
};
