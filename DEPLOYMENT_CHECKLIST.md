# CruiseLine — Deployment Checklist

This app currently runs **locally** (frontend Vite dev server on :5173, Spring Boot
backend on :8082, local MySQL). It works fully in that setup. Before deploying it to
a server that other people can reach, the items below must be addressed. None of this
is a rewrite — it's configuration for a production environment.

Tackle these in order. Each is "must do" unless marked optional.

---

## 1. Frontend — build instead of dev server
- [ ] Run `npm run build` (produces a `dist/` folder of static files). Do NOT deploy
      `npm run dev` — that's development only.
- [ ] Serve the `dist/` folder via a static host (Netlify, Vercel, GitHub Pages) or a
      web server (nginx). 
- [ ] Update `.env` (or the host's env settings): `VITE_API_BASE_URL` must point to the
      **deployed backend URL** (e.g. `https://api.yourdomain.com`), NOT `http://localhost:8082`.
      Note: Vite bakes env vars in at BUILD time, so set this before running `npm run build`.

## 2. Backend — externalize config (no hardcoded localhost/secrets)
- [ ] **Database:** `application.properties` currently points to local MySQL. Point it at
      a hosted DB via env vars: `spring.datasource.url`, `username`, `password` should read
      from environment (e.g. `${DB_URL}`, `${DB_USER}`, `${DB_PASSWORD}`).
- [ ] **JWT_SECRET:** set as an env var on the server (a strong, secret value). Already
      done locally — just ensure it's set in the deployment environment too.
- [ ] **Token expiry:** shorten for production. Set `JWT_ACCESS_EXP` (e.g. 900000 = 15 min).
      The 24h default is too long for a public deployment.
- [ ] **CORS:** the backend currently allows `http://localhost:5173`. Change the allowed
      origin(s) to your real frontend domain (e.g. `https://yourdomain.com`), or the browser
      will block every request. (Look for the CORS config in the security configuration.)
- [ ] **Schema:** `spring.jpa.hibernate.ddl-auto=update` is fine for dev but risky in prod
      (it auto-alters the DB). For production prefer `validate` + a migration tool
      (Flyway/Liquibase), or at least be aware of the risk.
- [ ] Build a deployable jar: `mvn clean package` → produces `target/*.jar` you run with
      `java -jar`.

## 3. Database — hosted and always-on
- [ ] Provision a hosted MySQL (cloud DB, or MySQL on the same server). Local MySQL
      disappears when your machine sleeps.
- [ ] Run the app once against it so the schema is created (or run migrations).
- [ ] Seed the admin user (DataInitializer does this on first run:
      admin@cruiseline.com / Admin@12345 — CHANGE this password in production).

## 4. HTTPS (required for a public deployment)
- [ ] Serve both frontend and backend over `https://` (TLS certificate — many hosts provide
      this automatically, e.g. Let's Encrypt, or built into Netlify/Vercel/Render).
- [ ] Sending passwords and JWTs over plain http is unsafe; do not skip this for a public URL.

## 5. Small code gotchas to revisit
- [ ] `src/api/client.js` uses `window.location.href = '/login'` on auth failure — assumes
      the app is served at the domain root. If deployed under a sub-path, adjust.
- [ ] Confirm the frontend router (BrowserRouter) works on your host — SPAs need the host
      configured to serve `index.html` for all routes (a "rewrite all to index.html" rule),
      otherwise refreshing a deep link like /bookings 404s.

## 6. Security hardening (good practice before going public)
- [ ] Change the default admin password.
- [ ] Shorten token lifetimes (see 2).
- [ ] Consider whether the backend refresh endpoint should invalidate old tokens
      (true token revocation needs a server-side token store — a larger feature, optional).
- [ ] Review that no secrets are committed to source control (.env, application.properties
      with real credentials).

---

## Suggested hosting shapes (pick one when ready)
- **Simple split:** frontend on Netlify/Vercel (free, auto-HTTPS), backend + MySQL on
  Railway/Render (handle env vars + HTTPS for you). Easiest for a first deployment.
- **Single VM:** one cloud server (e.g. a small VPS) running nginx (serves frontend +
  reverse-proxies the backend), the Spring Boot jar, and MySQL. More control, more setup.

## Note on the corporate machine
You mentioned a locked-down corporate Windows machine. Check whether your environment
even permits hosting / outbound deployment before planning — you may need a personal
cloud account or approval.

---

When you're ready to deploy, start a fresh chat, upload your current `src.zip` + the
backend (or the relevant config files), paste a short recap of the project, and say which
host you've chosen. Then work through this checklist item by item.
