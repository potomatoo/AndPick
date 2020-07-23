<template>
  <footer>
    <div class="container">
      <router-link :to="{ name: 'Home' }">
        <a class="logo-font">Junho</a>
      </router-link>
      <span class="attribution">
        An interactive learning project from
        <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp" target="_blank"
          >SSAFY</a
        >
        .
      </span>
    </div>
  </footer>
</template>

<style scoped>
footer {
  text-align: end;
}
a {
  color: #5cb85c !important;
}
</style>
