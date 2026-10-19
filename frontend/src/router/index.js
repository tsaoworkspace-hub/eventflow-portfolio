import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", redirect: "/calendar" },
    {
      path: "/calendar",
      name: "calendar",
      component: () => import("@/views/calendar/CalendarHomeView.vue")
    },
    {
      path: "/notifications",
      name: "notifications",
      component: () => import("@/views/notification/NotificationHomeView.vue")
    },
    {
      path: "/admin/events",
      name: "admin-events",
      component: () => import("@/views/admin/calendarAndNotification/AdminActivityManagementView.vue")
    },
    {
      path: "/admin/notifications",
      name: "admin-notifications",
      component: () => import("@/views/admin/calendarAndNotification/AdminNotificationCenterView.vue")
    },
    {
      path: "/admin/dashboard",
      name: "admin-dashboard",
      component: () => import("@/views/admin/calendarAndNotification/AdminActivityOverviewView.vue")
    },
    { path: "/:pathMatch(.*)*", redirect: "/calendar" }
  ],
  scrollBehavior: () => ({ top: 0 })
});

export default router;
