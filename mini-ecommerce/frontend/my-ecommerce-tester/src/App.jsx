import React, { useEffect, useMemo, useState } from 'react'
import { api, getBaseUrl, setBaseUrl } from './api'

const defaultBase = getBaseUrl()

function JsonView({ data }) {
  if (data === undefined) return null
  return (
    <pre style={{
      background: '#0b1220', color: '#e2e8f0', padding: 12,
      borderRadius: 8, border: '1px solid #1f2937', overflow: 'auto', maxHeight: 360
    }}>
      {typeof data === 'string' ? data : JSON.stringify(data, null, 2)}
    </pre>
  )
}

export default function App() {
  const [baseUrl, setBase] = useState(defaultBase)
  const [loading, setLoading] = useState(false)
  const [result, setResult] = useState()
  const [error, setError] = useState()

  // Inputs for specific calls
  const [productId, setProductId] = useState('1001')
  const [customerId, setCustomerId] = useState('1')

  // Form for creating product
  const [newProduct, setNewProduct] = useState({ name: 'Test Vite Product', price: 1234.56, categoryId: 1 })
  // Form for creating customer
  const [newCustomer, setNewCustomer] = useState({ name: 'Cliente Vite', email: 'cliente@vite.dev' })

  useEffect(() => {
    setBaseUrl(baseUrl)
  }, [baseUrl])

  const endpoints = useMemo(() => ({
    listProducts: '/api/v1/ecommerce/products',
    getProduct: `/api/v1/ecommerce/products/${productId}`,
    createProduct: '/api/v1/ecommerce/products',
    listCustomers: '/api/v1/ecommerce/customers',
    getCustomer: `/api/v1/ecommerce/customers/${customerId}`,
    createCustomer: '/api/v1/ecommerce/customers',
    customerOrders: `/api/v1/ecommerce/customers/${customerId}/orders`,
  }), [productId, customerId])

  async function run(name, fn) {
    setLoading(true)
    setError(undefined)
    setResult(undefined)
    try {
      const data = await fn()
      setResult({ name, data })
    } catch (e) {
      setError({ message: e.message, status: e.status, data: e.data })
    } finally {
      setLoading(false)
    }
  }

  const card = { background: '#111827', border: '1px solid #1f2937', borderRadius: 12, padding: 16 }
  const label = { display: 'block', fontSize: 12, color: '#93c5fd', marginBottom: 6 }
  const input = { width: '100%', padding: '8px 10px', borderRadius: 8, border: '1px solid #334155', background: '#0b1220', color: '#e2e8f0' }
  const button = { background: '#2563eb', color: '#fff', border: 0, borderRadius: 8, padding: '8px 12px', cursor: 'pointer' }

  return (
    <div style={{ fontFamily: 'system-ui, Segoe UI, Roboto, Arial', background:'#0f172a', minHeight: '100vh', color:'#e2e8f0' }}>
      <div style={{ maxWidth: 1100, margin: '0 auto', padding: '24px 16px' }}>
        <h1 style={{ marginTop: 0 }}>My E‑Commerce API Tester</h1>
        <p style={{ color: '#cbd5e1' }}>Prueba tu API de forma remota. Configura la URL base y ejecuta llamadas comunes.</p>

        <div style={{ display: 'grid', gap: 16 }}>
          <section style={card}>
            <label style={label}>API Base URL</label>
            <div style={{ display: 'flex', gap: 8 }}>
              <input style={input} value={baseUrl} onChange={e => setBase(e.target.value)} placeholder="http://<host>:8080" />
              <button style={button} onClick={() => setBase(defaultBase)}>Reset</button>
            </div>
            <p style={{ marginTop: 8, color: '#94a3b8' }}>Sugerencia local: http://localhost:8080</p>
          </section>

          <section style={card}>
            <h3 style={{ marginTop: 0 }}>Consultas rápidas (GET)</h3>
            <div style={{ display: 'grid', gap: 10, gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))' }}>
              <button disabled={loading} style={button} onClick={() => run('Listar productos', () => api.get(endpoints.listProducts))}>GET /products</button>
              <div style={{ display: 'flex', gap: 6 }}>
                <input style={{ ...input, flex: 1 }} value={productId} onChange={e => setProductId(e.target.value)} />
                <button disabled={loading} style={button} onClick={() => run('Obtener producto', () => api.get(endpoints.getProduct))}>GET /products/{'{id}'}</button>
              </div>
              <button disabled={loading} style={button} onClick={() => run('Listar clientes', () => api.get(endpoints.listCustomers))}>GET /customers</button>
              <div style={{ display: 'flex', gap: 6 }}>
                <input style={{ ...input, flex: 1 }} value={customerId} onChange={e => setCustomerId(e.target.value)} />
                <button disabled={loading} style={button} onClick={() => run('Obtener cliente', () => api.get(endpoints.getCustomer))}>GET /customers/{'{id}'}</button>
              </div>
              <button disabled={loading} style={button} onClick={() => run('Órdenes del cliente', () => api.get(endpoints.customerOrders))}>GET /customers/{'{id}'}/orders</button>
            </div>
          </section>

          <section style={card}>
            <h3 style={{ marginTop: 0 }}>Crear Product (POST)</h3>
            <div style={{ display: 'grid', gap: 8, gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))' }}>
              <div>
                <label style={label}>Name</label>
                <input style={input} value={newProduct.name} onChange={e => setNewProduct(p => ({ ...p, name: e.target.value }))} />
              </div>
              <div>
                <label style={label}>Price</label>
                <input type="number" step="0.01" style={input} value={newProduct.price} onChange={e => setNewProduct(p => ({ ...p, price: Number(e.target.value) }))} />
              </div>
              <div>
                <label style={label}>CategoryID</label>
                <input type="number" style={input} value={newProduct.categoryId} onChange={e => setNewProduct(p => ({ ...p, categoryId: Number(e.target.value) }))} />
              </div>
            </div>
            <div style={{ marginTop: 8 }}>
              <button disabled={loading} style={button} onClick={() => run('Crear producto', () => api.post(endpoints.createProduct, newProduct))}>POST /products</button>
            </div>
          </section>

          <section style={card}>
            <h3 style={{ marginTop: 0 }}>Crear Customer (POST)</h3>
            <div style={{ display: 'grid', gap: 8, gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))' }}>
              <div>
                <label style={label}>Name</label>
                <input style={input} value={newCustomer.name} onChange={e => setNewCustomer(p => ({ ...p, name: e.target.value }))} />
              </div>
              <div>
                <label style={label}>Email</label>
                <input type="email" style={input} value={newCustomer.email} onChange={e => setNewCustomer(p => ({ ...p, email: e.target.value }))} />
              </div>
            </div>
            <div style={{ marginTop: 8 }}>
              <button disabled={loading} style={button} onClick={() => run('Crear cliente', () => api.post(endpoints.createCustomer, newCustomer))}>POST /customers</button>
            </div>
          </section>

          <section style={card}>
            <h3 style={{ marginTop: 0 }}>Resultado</h3>
            <div style={{ fontSize: 12, color: '#93c5fd', marginBottom: 6 }}>Base URL actual: {baseUrl}</div>
            {loading && <div>Ejecutando...</div>}
            {error && (
              <div style={{ color: '#fecaca', marginBottom: 8 }}>
                <div><strong>Error:</strong> {error.message}</div>
                {error.status ? <div>Status: {error.status}</div> : null}
                {error.data ? <JsonView data={error.data} /> : null}
              </div>
            )}
            {result && (
              <div>
                <div style={{ marginBottom: 8 }}><strong>{result.name}</strong></div>
                <JsonView data={result.data} />
              </div>
            )}
          </section>
        </div>

        <p style={{ color: '#94a3b8', marginTop: 16 }}>
          Consejo: Si tu API no permite CORS, ejecuta este tester con <code>npm run dev</code> y usa rutas que empiecen con <code>/api</code> para que el proxy del dev server las reenvíe al backend.
        </p>
      </div>
    </div>
  )
}
