package co.edu.unbosque.frontTecno;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Detalle_VentaJSON {
	
	private static URL url;
	private static String sitio = "http://localhost:8083/";	
	
	public static ArrayList<Detalle_Venta> parsingDetalle_Venta(String json) throws ParseException {
        
        JSONParser jsonParser = new JSONParser();
        ArrayList<Detalle_Venta> lista = new ArrayList<Detalle_Venta>();
        JSONArray detalle_venta = (JSONArray) jsonParser.parse(json);
        Iterator i = detalle_venta.iterator();
        
        while (i.hasNext()) {
        
            JSONObject innerObj = (JSONObject) i.next();
            
            Detalle_Venta detalle = new Detalle_Venta();
            
            detalle.setCodigo_venta(Long.parseLong(innerObj.get("codigo_venta").toString()));
            detalle.setCodigo_producto(String.valueOf(innerObj.get("codigo_producto").toString()));
            detalle.setCantidad_producto(Integer.parseInt(innerObj.get("cantidad_producto").toString()));
            detalle.setValor_venta(Double.parseDouble(innerObj.get("valor_venta").toString()));
            detalle.setValor_iva(Double.parseDouble(innerObj.get("valor_iva").toString()));
            detalle.setValor_total(Double.parseDouble(innerObj.get("valor_total").toString()));
            lista.add(detalle);
        }
        return lista;
    }
	
	public static ArrayList<Detalle_Venta> getJSON() throws IOException, ParseException{
        
        url = new URL(sitio +"api/detalle_venta/listar");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");
        
        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = "";
        
        for (int i = 0; i<inp.length ; i++) {
           json += (char)inp[i];
        }
        
        ArrayList<Detalle_Venta> lista = new ArrayList<Detalle_Venta>();
        lista = parsingDetalle_Venta(json);
        http.disconnect();
        return lista;
    }
	
	//*********Método que consume la API de guardar*********************************
		public static int postJSON(Detalle_Venta detalle_venta) throws IOException {
			
			url = new URL(sitio + "api/detalleventa/guardar");
			HttpURLConnection http;
			http = (HttpURLConnection)url.openConnection();
			
			try {
				http.setRequestMethod("POST");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}
			
			http.setDoOutput(true);
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/json");
			
			String data = "{" 
					+ "\"codigo_venta\":\"" + String.valueOf(detalle_venta.getCodigo_venta())				
					+ "\",\"codigo_producto\": \"" + String.valueOf(detalle_venta.getCodigo_producto()) 
					+ "\",\"cantidad_producto\": \""  + String.valueOf(detalle_venta.getCantidad_producto()) 
					+ "\",\"valor_total\":\"" + String.valueOf(detalle_venta.getValor_total()) 
					+ "\",\"valor_venta\":\"" + String.valueOf(detalle_venta.getValor_venta())
					+ "\",\"valor_iva\":\"" + String.valueOf(detalle_venta.getValor_iva())
					+ "\"}";
			
			byte[] out = data.getBytes(StandardCharsets.UTF_8);
			OutputStream stream = http.getOutputStream();
			stream.write(out);
			
			int respuesta = http.getResponseCode();
			http.disconnect();
			return respuesta;
		}

}