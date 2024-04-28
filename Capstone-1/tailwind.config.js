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
      fontFamily: {
        primary: ["Bebas Neue", "sans-serif"],
        secondary: ["Poppins", "sans-serif"],
      },
      colors: {
        formbg: {
          dark: "#D3AC09",
          light: "#E9D790",
        },
      },
      backgroundColor: {
        tanglow: "#FDCC01",
        gold: "#B6B6B6",
      },
    },
  },
  plugins: [],
};
