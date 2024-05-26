/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.html"],
  theme: {
    extend: {
      width: {
        960: "960px",
        642: "542px",
        w45: "45%",
        w50: "50%",
      },
      height: {
        520: "520px",
        444: "444px",
        300: "230px",
        1080: "1080px",
        722: "722px",
        110: "110px",
        200: "200px",
        150: "170px",
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
        customyellow: "#FDCC01",
      },
      backgroundColor: {
        tanglow: "#FDCC01",
        gold: "#B6B6B6",
      },
      borderWidth: {
        10: "10px",
      },
      borderRadius: {
        "radius-40": "40px",
      },
      transitionDuration: {
        300: "300ms", // You can add more durations if needed
      },
      transitionTimingFunction: {
        "ease-in": "ease-in", // You can add more timing functions if needed
      },
      transform: {
        transitionProperty: "transform",
        transitionTimingFunction: "cubic-bezier(0.4, 0, 0.2, 1)",
        transitionDuration: "300ms",
      },
      backgroundImage: {
        test: "url('/Capstone1/Capstone-1/src/main/resources/static/images/darkenlandingpageImg.png')",
      },
    },
  },
  plugins: [],
};
