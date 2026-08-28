using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ClickbusApi.Data;
using ClickbusApi.Models;

namespace ClickbusApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RodoviariaController : ControllerBase
    {
        private readonly AppDbContext _context;

        public RodoviariaController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/rodoviaria/1/embarque/Portão 08
        [HttpGet("{id}/embarque/{portao}")]
        public async Task<ActionResult<object>> GetDadosEmbarque(int id, string portao)
        {
            var rodoviaria = await _context.Rodoviarias.FindAsync(id);

            if (rodoviaria == null)
            {
                return NotFound(new { Message = "Rodoviária não encontrada" });
            }

            // Simulação de retorno de dados para o Módulo RA
            return Ok(new
            {
                RodoviariaNome = rodoviaria.Nome,
                SsidWifi = rodoviaria.SsidWifiLocal,
                PortaoAlvo = portao,
                LayoutConfig = rodoviaria.DadosLayoutJson,
                Instrucoes = "Dirija-se ao setor B, siga as setas roxas no chão."
            });
        }
    }
}
