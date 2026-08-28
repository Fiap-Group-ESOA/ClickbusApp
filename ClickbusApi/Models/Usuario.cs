using System.ComponentModel.DataAnnotations;

namespace ClickbusApi.Models
{
    public class Usuario
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(100)]
        public string Nome { get; set; } = string.Empty;

        [Required]
        [EmailAddress]
        public string Email { get; set; } = string.Empty;

        [Required]
        [StringLength(14)]
        public string CPF { get; set; } = string.Empty;

        [Required]
        public string SenhaHash { get; set; } = string.Empty;

        public bool NecessitaAcessibilidade { get; set; } = false;

        // Relacionamento com Passagens
        public ICollection<Passagem> Passagens { get; set; } = new List<Passagem>();
    }
}
