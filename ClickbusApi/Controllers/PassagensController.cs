using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ClickbusApi.Data;
using ClickbusApi.Models;

namespace ClickbusApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PassagensController : ControllerBase
    {
        private readonly AppDbContext _context;

        public PassagensController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/passagens/usuario/1
        [HttpGet("usuario/{usuarioId}")]
        public async Task<ActionResult<IEnumerable<Passagem>>> GetPassagensPorUsuario(int usuarioId)
        {
            return await _context.Passagens
                .Include(p => p.Rodoviaria)
                .Where(p => p.UsuarioId == usuarioId && p.Status == "Confirmada")
                .ToListAsync();
        }

        // GET: api/passagens/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Passagem>> GetPassagem(int id)
        {
            var passagem = await _context.Passagens
                .Include(p => p.Rodoviaria)
                .FirstOrDefaultAsync(p => p.Id == id);

            if (passagem == null)
            {
                return NotFound();
            }

            return passagem;
        }
    }
}
