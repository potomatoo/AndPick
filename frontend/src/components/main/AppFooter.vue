<template>
  <footer>
    <div class="container row">
      <div class="d-flex justify-content-end">
        <router-link :to="{ name: 'Home' }">
          <a class="logo-font">Junho</a>
        </router-link>
        <span class="attribution">
          An interactive learning project from
          <a href="https://thinkster.io">Thinkster</a>. Code &amp; design licensed
          under MIT.
        </span>
      </div>
    </div>
  </footer>
</template>
