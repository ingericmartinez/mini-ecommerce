import { defineConfig, loadEnv } from 'vite'

export default ({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  const target = env.VITE_API_BASE_URL || 'http://localhost:8080'

  return defineConfig({
    server: {
      port: 5173,
      open: true,
      proxy: {
        // Opcional: si llamas a rutas que inician con /api y tu API está en otro origen,
        // el dev server de Vite reenvía al backend para evitar CORS durante desarrollo.
        '/api': {
          target,
          changeOrigin: true,
          secure: false,
        },
      },
    },
  })
}
