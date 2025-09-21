# My E‑Commerce API Tester (Vite + React)

Pequeña app hecha con Vite (JavaScript) para probar la API del proyecto de forma local o remota.

## Requisitos
- Node.js 18+
- npm 9+

## Instalación y ejecución
```bash
cd frontend/my-ecommerce-tester
npm install
# (opcional) Copia variables de entorno
cp .env.example .env
# Ejecutar en modo desarrollo
npm run dev
```
El navegador se abrirá en http://localhost:5173.

## Configurar la API remota
- Puedes establecer la URL base de la API de dos formas:
  1) Variable de entorno VITE_API_BASE_URL en `.env` (ej. `VITE_API_BASE_URL=https://mi-api-remota.azurewebsites.net`)
  2) En la propia UI, arriba en "API Base URL". Se guarda en `localStorage`.

Sugerencia local: `http://localhost:8080`.

## Endpoints preconfigurados
La app ya trae botones para:
- GET /api/v1/ecommerce/products
- GET /api/v1/ecommerce/products/{id}
- POST /api/v1/ecommerce/products
- GET /api/v1/ecommerce/customers
- GET /api/v1/ecommerce/customers/{id}
- POST /api/v1/ecommerce/customers
- GET /api/v1/ecommerce/customers/{id}/orders

Estos paths coinciden con los controladores del backend.

## Evitar CORS en desarrollo
Si tu API no habilita CORS, puedes llamar usando rutas que empiecen con `/api` y el dev server de Vite las reenviará al backend configurado por `VITE_API_BASE_URL` (ver `vite.config.js`).

## Build de producción
```bash
npm run build
npm run preview
```
Servirá los archivos estáticos generados en `dist/` en http://localhost:5173.
