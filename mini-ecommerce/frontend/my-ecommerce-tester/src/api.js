const DEFAULT_BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

function getStoredBase() {
  try {
    return localStorage.getItem('apiBaseUrl') || DEFAULT_BASE
  } catch {
    return DEFAULT_BASE
  }
}

export function setBaseUrl(url) {
  try {
    localStorage.setItem('apiBaseUrl', url)
  } catch {}
}

export function getBaseUrl() {
  return getStoredBase()
}

async function request(path, options = {}) {
  const base = getBaseUrl().replace(/\/$/, '')
  const url = path.startsWith('http') ? path : `${base}${path}`

  const headers = { 'Content-Type': 'application/json', ...(options.headers || {}) }
  const init = { ...options, headers }

  const res = await fetch(url, init)
  const text = await res.text()
  let data
  try {
    data = text ? JSON.parse(text) : null
  } catch {
    data = text
  }
  if (!res.ok) {
    const err = new Error(`HTTP ${res.status} ${res.statusText}`)
    err.status = res.status
    err.data = data
    throw err
  }
  return data
}

export const api = {
  get: (path) => request(path, { method: 'GET' }),
  post: (path, body) => request(path, { method: 'POST', body: JSON.stringify(body) }),
}
