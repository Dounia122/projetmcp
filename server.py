from mcp.server.fastmcp import FastMCP
import json  # 👈 nécessaire pour la sérialisation

mcp = FastMCP('Python-MCP-Server')

@mcp.tool()
def get_info_about(name: str) -> str:
    """
    Get Information about a given employee name:
    - First Name
    - Last Name
    - Salary
    - Email
    """
    return json.dumps({
        "first_name": name,
        "last_name": "Mohamed",
        "salary": 5400,
        "email": "med@gmail.com"
    })
