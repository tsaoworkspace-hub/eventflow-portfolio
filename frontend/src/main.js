import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/index.js";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "leaflet/dist/leaflet.css";
import "./assets/css/portfolio.css";

createApp(App).use(router).mount("#app");
