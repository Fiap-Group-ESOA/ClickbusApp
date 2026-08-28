using Microsoft.EntityFrameworkCore;
using ClickbusApi.Models;

namespace ClickbusApi.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Passagem> Passagens { get; set; }
        public DbSet<Rodoviaria> Rodoviarias { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Seed Data para Testes
            var rodoviariaTiete = new Rodoviaria
            {
                Id = 1,
                Nome = "Terminal Rodoviário Tietê",
                Cidade = "São Paulo",
                SsidWifiLocal = "Clickbus_Free_Wifi",
                DadosLayoutJson = "{\"gates\": [{\"id\": \"08\", \"coords\": \"x:10,y:20\"}], \"pois\": [{\"name\": \"Banheiro PCD\", \"coords\": \"x:5,y:5\"}]}"
            };

            modelBuilder.Entity<Rodoviaria>().HasData(rodoviariaTiete);

            modelBuilder.Entity<Usuario>().HasData(new Usuario
            {
                Id = 1,
                Nome = "João Silva",
                Email = "joao@example.com",
                CPF = "123.456.789-00",
                SenhaHash = "AQAAAAEAACcQAAAAE...", // Exemplo de hash
                NecessitaAcessibilidade = true
            });

            modelBuilder.Entity<Passagem>().HasData(new Passagem
            {
                Id = 1,
                UsuarioId = 1,
                Origem = "São Paulo",
                Destino = "Rio de Janeiro",
                DataHora = DateTime.Now.AddHours(2),
                RodoviariaId = 1,
                PortaoEmbarque = "Portão 08 - Plataforma B",
                Status = "Confirmada"
            });
        }
    }
}
