package gg.jte.generated.ondemand;
import com.oauth.dto.UserDTO;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,12,12,19,29,39,52,73,73,73,74,74,75,75,75,76,76,81,81,81,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UserDTO usuario) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n  <meta charset=\"UTF-8\" />\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n  <title>User Info</title>\n  <link rel=\"stylesheet\" href=\"styles.css\" />\n  <style>\n    ");
		jteOutput.writeContent("\n* {\n  margin: 0;\n  padding: 0;\n  box-sizing: border-box;\n}\n\n");
		jteOutput.writeContent("\nbody {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100vh;\n  font-family: Arial, sans-serif;\n  background-color: #f3f4f6;\n}\n\n");
		jteOutput.writeContent("\n.user-card {\n  background-color: #ffffff;\n  padding: 20px 40px;\n  border-radius: 8px;\n  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n  text-align: center;\n  width: 300px;\n}\n\n");
		jteOutput.writeContent("\n.user-card h2 {\n  font-size: 1.5rem;\n  color: #333333;\n  margin-bottom: 8px;\n}\n\n.user-card p {\n  font-size: 1rem;\n  color: #666666;\n  margin-bottom: 20px;\n}\n\n");
		jteOutput.writeContent("\n.user-card .logout-btn {\n  padding: 10px 20px;\n  font-size: 1rem;\n  font-weight: bold;\n  color: #ffffff;\n  background-color: #ff4d4d;\n  border: none;\n  border-radius: 5px;\n  cursor: pointer;\n  transition: background-color 0.3s ease;\n}\n\n.user-card .logout-btn:hover {\n  background-color: #e60000;\n}\n\n  </style>\n</head>\n<body>\n  <div class=\"user-card\">\n    <h2>Hello: ");
		jteOutput.setContext("h2", null);
		jteOutput.writeUserContent(usuario.userName());
		jteOutput.writeContent("</h2>\n     ");
		if (!usuario.userName().isEmpty()) {
			jteOutput.writeContent("\n      <p>Email : ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(usuario.email());
			jteOutput.writeContent("</p>\n     ");
		}
		jteOutput.writeContent("\n    <a href=\"/logout\" class=\"logout-btn\">Logout</a>\n  </div>\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UserDTO usuario = (UserDTO)params.get("usuario");
		render(jteOutput, jteHtmlInterceptor, usuario);
	}
}
