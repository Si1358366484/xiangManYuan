<script setup>
import Layout from './components/Layout.vue'
import bgImage from './assets/images/home/hg.png'
</script>

<template>
  <div class="app-wrapper" :style="{ backgroundImage: `url(${bgImage})` }">
    <div class="overlay"></div>
    <Layout />
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body, html {
  width: 100%;
  height: 100%;
  overflow: hidden;
}

#app {
  width: 100%;
  height: 100vh;
  max-width: 100%;
  margin: 0;
  padding: 0;
}

.app-wrapper {
  width: 100%;
  height: 100vh;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: relative;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 0;
}

.app-wrapper > :not(.overlay) {
  position: relative;
  z-index: 1;
}
</style>
