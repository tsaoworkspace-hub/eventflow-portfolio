<script setup>
const links = [
  { to: "/calendar", label: "活動行事曆" },
  { to: "/notifications", label: "會員通知" },
  { to: "/admin/events", label: "活動管理" },
  { to: "/admin/notifications", label: "通知管理" },
  { to: "/admin/dashboard", label: "成效儀表板" }
];
</script>

<template>
  <div class="portfolio-shell">
    <header class="portfolio-header">
      <RouterLink class="portfolio-brand" to="/calendar">
        <span class="brand-mark">EF</span>
        <span>
          <strong>EventFlow</strong>
          <small>Calendar & Notification Portfolio</small>
        </span>
      </RouterLink>

      <nav class="portfolio-nav" aria-label="作品功能導覽">
        <RouterLink v-for="link in links" :key="link.to" :to="link.to">
          {{ link.label }}
        </RouterLink>
      </nav>

      <div class="demo-badge">DEMO MODE</div>
    </header>

    <main>
      <RouterView />
    </main>

    <footer class="portfolio-footer">
      <p>Java 17 · Spring Boot · JPA · Redis Stream · Vue 3 · REST API</p>
      <p>由曹志宗負責原團隊專案中的活動、行事曆與通知模組；本版本為可獨立執行的作品集抽出版。</p>
    </footer>
  </div>
</template>
