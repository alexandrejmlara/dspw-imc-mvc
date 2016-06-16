package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class IMCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String valor(
			HttpServletRequest req,
			String param,
			String padrao) {

		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}

	private float toFloat(
			HttpServletRequest req,
			String param,
			String padrao) {
		return Float.parseFloat(valor(req, param, padrao));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		float height = toFloat(req, "height", "0");
		float weight = toFloat(req, "weight", "0");
		String result = "";

		float resultadoCalculo = IMCModel.calcular(height, weight);
				
		if(resultadoCalculo < 16){
			result = "Seu IMC indica -> Baixo peso muito grave: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 16 && resultadoCalculo < 17){
			result = "Seu IMC indica -> Baixo peso grave: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 17 && resultadoCalculo < 18.50) {
			result = "Seu IMC indica -> Baixo peso: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 18.50 && resultadoCalculo < 25) {
			result = "Seu IMC indica -> Peso normal: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 25 && resultadoCalculo < 30) {
			result = "Seu IMC indica -> Sobrepeso: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 30 && resultadoCalculo < 35) {
			result = "Seu IMC indica -> Obesidade grau I: " + resultadoCalculo;
		}
		else if(resultadoCalculo >= 35 && resultadoCalculo < 40) {
			result = "Seu IMC indica -> Obesidade grau II: " + resultadoCalculo;
		}
		else if (resultadoCalculo >= 40)
			result = "Seu IMC indica -> Obesidade grau III: " + resultadoCalculo;
		
		
		//Passando parâmetro para o JSP.
		req.setAttribute("result", result);

		req.getRequestDispatcher("CalculadoraView.jsp").forward(req, resp);
	}
}
